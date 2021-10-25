package io.github.picodotdev.blogbitix.springresthateoas;

...

@RestController
@ExposesResourceFor(Message.class)
public class MessageController implements MessageApi {

    private MessageModelAssembler assembler;
    private Map<Long, Message> messages;

    public MessageController(MessageModelAssembler assembler) {
        this.assembler = assembler;

        this.messages = new HashMap<>();
        this.messages.put(1l, new Message(1l, "Hello World!"));
        this.messages.put(2l, new Message(2l, "Welcome to Blog Bitix!"));
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<Message>>> index() {
        try {
            Collection<Link> links = List.of(
                Link.of(linkTo(MessageController.class).toUriComponentsBuilder().path(MessageApi.class.getMethod("index").getAnnotation(GetMapping.class).value()[0]).build().toUriString()).withSelfRel(),
                Link.of(linkTo(MessageController.class).toUriComponentsBuilder().path(MessageApi.class.getMethod("getAll").getAnnotation(GetMapping.class).value()[0]).build().toUriString()).withRel("getAll"),
                Link.of(linkTo(MessageController.class).toUriComponentsBuilder().path(MessageApi.class.getMethod("getById", Long.class).getAnnotation(GetMapping.class).value()[0]).build().toUriString()).withRel("getById"),
                Link.of(linkTo(MessageController.class).toUriComponentsBuilder().path(MessageApi.class.getMethod("add", Message.class).getAnnotation(PostMapping.class).value()[0]).build().toUriString()).withRel("add"),
                Link.of(linkTo(MessageController.class).toUriComponentsBuilder().path(MessageApi.class.getMethod("deleteById", Long.class).getAnnotation(DeleteMapping.class).value()[0]).build().toUriString()).withRel("deleteById")
            );
            return ResponseEntity.ok(CollectionModel.empty(links));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<Message>>> getAll() {
        List<Message> entities = messages.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return ResponseEntity.ok(assembler.toCollectionModel(entities));
    }

    @Override
    public ResponseEntity<EntityModel<Message>> getById(Long id) {
        if (!exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
        return ResponseEntity.ok(assembler.toModel(messages.get(id)));
    }

    @Override
    public ResponseEntity<Void> add(Message message) {
        if (exists(message.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already exists");
        }
        if (message.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
        }
        messages.put(message.getId(), message);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        if (!exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
        messages.remove(id);
        return ResponseEntity.ok().build();
    }

    private boolean exists(Long id) {
        return messages.containsKey(id);
    }
}
