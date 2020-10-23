package io.github.picodotdev.blogbitix.eventbus.domain.shared.repository;

...

public interface EventRepository {

    void add(Event event);
    boolean exists(Event event);
}
