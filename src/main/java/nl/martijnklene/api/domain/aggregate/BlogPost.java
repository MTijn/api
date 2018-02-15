package nl.martijnklene.api.domain.aggregate;

import nl.martijnklene.api.application.command.CreateBlogPostCommand;
import nl.martijnklene.api.domain.event.BlogPostCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class BlogPost {
    private UUID id;
    private String title;
    private String content;
    private String tags;
    private String author;
    private String publishedAt;
    private String updatedAt;

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

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @CommandHandler
    public BlogPost(CreateBlogPostCommand createBlogPostCommand) {
        apply(new BlogPostCreatedEvent(
                createBlogPostCommand.getId(),
                createBlogPostCommand.getTitle(),
                createBlogPostCommand.getContent(),
                createBlogPostCommand.getTags(),
                createBlogPostCommand.getAuthor()
        ));
    }
}
