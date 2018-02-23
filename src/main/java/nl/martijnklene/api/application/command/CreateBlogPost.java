package nl.martijnklene.api.application.command;

import nl.martijnklene.api.infrastructure.annotation.UniqueTitle;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.UUID;

public class CreateBlogPost {
    @TargetAggregateIdentifier
    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String tags;
    @NotEmpty
    @Email
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
