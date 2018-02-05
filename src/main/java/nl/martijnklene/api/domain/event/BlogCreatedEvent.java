package nl.martijnklene.api.domain.event;

import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;

import java.util.UUID;

public class BlogCreatedEvent extends DomainEventEntry {
    private final UUID id;
    private final String title;

    public BlogCreatedEvent(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
