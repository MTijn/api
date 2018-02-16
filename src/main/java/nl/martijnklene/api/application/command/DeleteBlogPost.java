package nl.martijnklene.api.application.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class DeleteBlogPost {
    @TargetAggregateIdentifier
    private UUID id;

    public DeleteBlogPost(UUID $id) {
        this.id = $id;
    }

    public UUID getId() {
        return id;
    }
}
