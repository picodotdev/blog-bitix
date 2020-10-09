package io.github.picodotdev.blogbitix.eventbus.domain.kernel.domainevent;

...

public class DomainEventId {

    private UUID id;

    public DomainEventId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return id.toString();
    }
}
