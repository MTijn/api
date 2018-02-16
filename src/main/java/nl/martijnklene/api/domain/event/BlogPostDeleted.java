package nl.martijnklene.api.domain.event;

import java.util.UUID;

public class BlogPostDeleted {
    private UUID id;

    public BlogPostDeleted(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
