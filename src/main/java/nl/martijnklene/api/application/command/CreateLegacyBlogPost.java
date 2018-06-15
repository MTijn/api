package nl.martijnklene.api.application.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

public class CreateLegacyBlogPost {
    @TargetAggregateIdentifier
    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String tags;
    private String author;
    private Date createdAt;
    private Date publishedAt;

    public CreateLegacyBlogPost(
            UUID id,
            @NotEmpty String title,
            @NotEmpty String content,
            String tags,
            String author,
            Date createdAt,
            Date publishedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }
}
