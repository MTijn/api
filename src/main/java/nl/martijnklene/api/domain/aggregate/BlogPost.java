package nl.martijnklene.api.domain.aggregate;

import nl.martijnklene.api.application.command.CreateBlogPost;
import nl.martijnklene.api.domain.event.BlogPostCreated;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class BlogPost implements Serializable{
    @AggregateIdentifier
    private UUID id;
    private String title;
    private String content;
    private String tags;
    private String author;

    @CommandHandler
    public BlogPost(CreateBlogPost createBlogPost) {
        this.id = createBlogPost.getId();
        apply(new BlogPostCreated(
                createBlogPost.getId(),
                createBlogPost.getTitle(),
                createBlogPost.getContent(),
                createBlogPost.getTags(),
                createBlogPost.getAuthor()
        ));
    }
}
