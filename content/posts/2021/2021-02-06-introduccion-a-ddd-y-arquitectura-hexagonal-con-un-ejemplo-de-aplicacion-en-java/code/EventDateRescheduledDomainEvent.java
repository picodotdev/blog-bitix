package io.github.picodotdev.blogbitix.dddhexagonal.catalog.domain.model.event;

...

public class EventDateRescheduledDomainEvent extends DomainEvent {

    private EventId eventId;

    public EventDateRescheduledDomainEvent(EventId eventId) {
        this.eventId = eventId;
    }

    public EventId getEventId() {
        return eventId;
    }
}