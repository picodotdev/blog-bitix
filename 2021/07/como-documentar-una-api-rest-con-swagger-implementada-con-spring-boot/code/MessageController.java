package io.github.picodotdev.blogbitix.springrestswagger;

...

@RestController
public class MessageController implements RestApi {

    private Map<Long, Message> messages;

    public RestApiController() {
        this.messages = new HashMap<>();
        this.messages.put(1l, new Message(1l, "Hello World!"));
        this.messages.put(2l, new Message(2l, "Welcome to Blog Bitix!"));
    }

    @Override
    public ResponseEntity<List<Message>> getAll() {
        List<Message> m = messages.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return ResponseEntity.ok(m);
    }

    @Override
    public ResponseEntity<Message> getById(Long id) {
        if (!exists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
        return ResponseEntity.ok(messages.get(id));
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
