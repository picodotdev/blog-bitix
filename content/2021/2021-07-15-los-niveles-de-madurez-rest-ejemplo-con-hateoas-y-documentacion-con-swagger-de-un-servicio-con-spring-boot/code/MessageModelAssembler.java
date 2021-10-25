package io.github.picodotdev.blogbitix.springresthateoas;

...

@Component
class MessageModelAssembler implements RepresentationModelAssembler<Message, EntityModel<Message>> {

    @Override
    public EntityModel<Message> toModel(Message message) {
        return EntityModel.of(message,
                linkTo(methodOn(MessageApi.class).getById(message.getId())).withSelfRel(),
                linkTo(methodOn(MessageApi.class).deleteById(message.getId())).withRel("deleteById"));
    }

    @Override
    public CollectionModel<EntityModel<Message>> toCollectionModel(Iterable<? extends Message> entities) {
        CollectionModel<EntityModel<Message>> model = RepresentationModelAssembler.super.toCollectionModel(entities);
        model.add(linkTo(methodOn(MessageController.class).getAll()).withSelfRel());
        model.add(Link.of(linkTo(MessageController.class).toUriComponentsBuilder().build().toUriString()).withRel("add"));
        return model;
    }
}
