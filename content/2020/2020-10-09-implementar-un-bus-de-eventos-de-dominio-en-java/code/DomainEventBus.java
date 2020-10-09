package io.github.picodotdev.blogbitix.eventbus.domain.kernel.domainevent;

import java.util.Collection;

public interface DomainEventBus {

    void publish(DomainEvent e);

    default void publish(Collection<DomainEvent> e) {
        e.stream().forEach(this::publish);
    }

    default void publish(DomainEventCollection c) {
        c.publish(this);
    }

    default void publish(AggregateRoot a) {
        publish(a.getEvents());
    }
}
