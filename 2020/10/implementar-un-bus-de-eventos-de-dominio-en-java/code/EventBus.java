package io.github.picodotdev.blogbitix.eventbus.domain.shared.eventbus;

...

public interface EventBus {

    void publish(Event e);

    default void publish(Collection<Event> events) {
        events.stream().forEach(this::publish);
    }

    default void publish(EventCollection collection) {
        collection.publish(this);
    }

    default void publish(AggregateRoot aggregate) {
        publish(aggregate.getEvents());
    }
}