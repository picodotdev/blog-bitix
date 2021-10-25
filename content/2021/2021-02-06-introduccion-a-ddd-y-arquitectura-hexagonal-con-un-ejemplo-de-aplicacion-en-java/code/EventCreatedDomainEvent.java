package io.github.picodotdev.blogbitix.dddhexagonal.catalog.domain.model.event;

...

public class EventCreatedDomainEvent extends DomainEvent {

    private EventId eventId;

    public EventCreatedDomainEvent(EventId eventId) {
        this.eventId = eventId;
    }

    public EventId getEventId() {
        return eventId;
    }
}