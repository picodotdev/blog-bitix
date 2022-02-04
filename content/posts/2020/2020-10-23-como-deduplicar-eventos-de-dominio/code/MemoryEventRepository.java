package io.github.picodotdev.blogbitix.eventbus.infrastructure;

...

@Component
public class MemoryEventRepository implements EventRepository {

    private Map<EventId, Event> store;

    public MemoryEventRepository() {
        store = new HashMap<>();
    }

    @Override
    public void add(Event event) {
        store.put(event.getId(), event);
    }

    @Override
    public boolean exists(Event event) {
        return store.containsKey(event.getId());
    }
}
