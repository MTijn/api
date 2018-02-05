package nl.martijnklene.api.application.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CreateBlogCommand {
    @TargetAggregateIdentifier
    private final UUID id;
    private final String title;

    public CreateBlogCommand(UUID blogId, String title) {
        this.id = blogId;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
