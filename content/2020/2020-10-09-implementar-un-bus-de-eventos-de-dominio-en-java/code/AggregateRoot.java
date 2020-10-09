package io.github.picodotdev.blogbitix.eventbus.domain.kernel.aggregate;

..

public interface AggregateRoot {

    DomainEventCollection getEvents();
}
