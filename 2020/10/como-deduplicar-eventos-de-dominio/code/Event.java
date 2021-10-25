package io.github.picodotdev.blogbitix.eventbus.domain.shared.eventbus;

...

public class Event {

    private EventId id;
    private LocalDateTime date;
    private Map<String, Object> data;

    public Event() {
        this(Collections.emptyMap());
    }

    public Event(Map<String, Object> data) {
        this.id = new EventId(UUID.randomUUID());
        this.date = LocalDateTime.now(ZoneId.of("UTC"));
        this.data = data;
    }

    public EventId getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
