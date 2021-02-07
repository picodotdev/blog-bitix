package io.github.picodotdev.blogbitix.dddhexagonal.catalog.application.usecases;

...

@Component
public class CreateEventUseCase {

    private EventRepository eventRepository;
    private DomainEventBus domainEventBus;

    public CreateEventUseCase(EventRepository eventRepository, DomainEventBus domainEventBus) {
        this.eventRepository = eventRepository;
        this.domainEventBus = domainEventBus;
    }

    public void handle(EventId id, EventSchedule schedule) throws Exception {
        Event event = Event.create(id, schedule);
        eventRepository.persist(event);

        domainEventBus.publish(event.getDomainEvents());
    }
}
