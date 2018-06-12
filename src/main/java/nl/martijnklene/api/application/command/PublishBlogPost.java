package nl.martijnklene.api.application.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

public class PublishBlogPost {
    @TargetAggregateIdentifier
    private final UUID id;
    private final Date publishedAt;

    public PublishBlogPost(UUID id, Date publishedAt) {
        this.id = id;
        this.publishedAt = publishedAt;
    }

    public UUID getId() {
        return id;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }
}
