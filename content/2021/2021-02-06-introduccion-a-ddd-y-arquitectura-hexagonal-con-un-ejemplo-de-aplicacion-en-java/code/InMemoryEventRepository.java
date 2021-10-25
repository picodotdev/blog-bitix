package io.github.picodotdev.blogbitix.dddhexagonal.catalog.infrastructure;

...

@Component
public class InMemoryEventRepository implements EventRepository {

    private AtomicLong sequence;
    private Map<EventId, Event> events;

    public InMemoryEventRepository() {
        this.sequence = new AtomicLong();
        this.events = new HashMap<>();
    }

    @Override
    public EventId getId() {
        Long id = sequence.addAndGet(1);
        return EventId.valueOf(new BigInteger(id.toString()));
    }

    @Override
    public Event findById(EventId id) {
        return events.get(id);
    }

    @Override
    public void persist(Event event) {
        events.put(event.getId(), event);
    }
}
