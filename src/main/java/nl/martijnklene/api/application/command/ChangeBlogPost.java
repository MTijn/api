package nl.martijnklene.api.application.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class ChangeBlogPost {
    @TargetAggregateIdentifier
    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String tags;
    private String author;

    public ChangeBlogPost(UUID id, String title, String content, String tags, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTags() {
        return tags;
    }

    public String getAuthor() {
        return author;
    }
}
