package io.github.picodotdev.blogbitix.eventbus.domain.shared.aggregateroot;

...

public interface AggregateRoot {

    EventCollection getEvents();
}