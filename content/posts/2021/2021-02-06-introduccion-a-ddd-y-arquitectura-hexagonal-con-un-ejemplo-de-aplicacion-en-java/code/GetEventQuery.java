package io.github.picodotdev.blogbitix.dddhexagonal.catalog.application.query;

...

public class GetEventQuery extends Query<Event> {

    private EventId eventId;

    public GetEventQuery(EventId eventId) {
        this.eventId = eventId;
    }

    public EventId getEventId() {
        return eventId;
    }

    public static class Builder {

        private EventId eventId;

        public static GetEventQuery.Builder getInstance() {
            return new GetEventQuery.Builder();
        }

        public GetEventQuery.Builder eventId(EventId eventId) {
            this.eventId = eventId;
            return this;
        }

        public GetEventQuery build() {
            return new GetEventQuery(eventId);
        }
    }
}
