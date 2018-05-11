package nl.martijnklene.api.application.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

public class CreateBlogPost {
    @TargetAggregateIdentifier
    private UUID id;
    private String title;
    private String content;
    private String tags;
    private String author;
    private Date createdAt;

    public CreateBlogPost(UUID id, String title, String content, String tags, String author, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
        this.createdAt = createdAt;
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
}
