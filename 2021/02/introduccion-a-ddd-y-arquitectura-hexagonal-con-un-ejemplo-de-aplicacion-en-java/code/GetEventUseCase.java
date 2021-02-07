package io.github.picodotdev.blogbitix.dddhexagonal.catalog.application.usecases;

...

@Component
public class GetEventUseCase {

    private EventRepository eventRepository;

    public GetEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event handle(EventId id) {
        return eventRepository.findById(id);
    }
}
