package nl.martijnklene.api.domain.aggregate;

import nl.martijnklene.api.application.command.CreateBlogPostCommand;
import nl.martijnklene.api.domain.event.BlogPostCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class BlogPost {
    @AggregateIdentifier
    private UUID id;
    private String title;
    private String content;
    private String tags;
    private String author;
    private String publishedAt;
    private String updatedAt;

    @CommandHandler
    public BlogPost(CreateBlogPostCommand createBlogPostCommand) {
        this.id = createBlogPostCommand.getId();
        apply(new BlogPostCreatedEvent(
                createBlogPostCommand.getId(),
                createBlogPostCommand.getTitle(),
                createBlogPostCommand.getContent(),
                createBlogPostCommand.getTags(),
                createBlogPostCommand.getAuthor()
        ));
    }
}
