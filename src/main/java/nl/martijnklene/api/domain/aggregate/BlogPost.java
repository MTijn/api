package nl.martijnklene.api.domain.aggregate;

import lombok.NoArgsConstructor;
import nl.martijnklene.api.application.command.ChangeBlogPost;
import nl.martijnklene.api.application.command.CreateBlogPost;
import nl.martijnklene.api.application.command.DeleteBlogPost;
import nl.martijnklene.api.domain.event.BlogPostChanged;
import nl.martijnklene.api.domain.event.BlogPostCreated;
import nl.martijnklene.api.domain.event.BlogPostDeleted;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@NoArgsConstructor
@Aggregate
public class BlogPost implements Serializable{
    @AggregateIdentifier
    private UUID id;
    private String title;
    private String content;
    private String tags;
    private String author;

    @SuppressWarnings("all")
    @CommandHandler
    public BlogPost(CreateBlogPost createBlogPost) {
        apply(new BlogPostCreated(
                createBlogPost.getId(),
                createBlogPost.getTitle(),
                createBlogPost.getContent(),
                createBlogPost.getTags(),
                createBlogPost.getAuthor()
        ));
    }

    @EventSourcingHandler
    public void blogPostCreated(BlogPostCreated blogPostCreated) {
        this.id = blogPostCreated.getId();
        this.title = blogPostCreated.getTitle();
        this.content = blogPostCreated.getContent();
        this.tags = blogPostCreated.getTags();
        this.author = blogPostCreated.getAuthor();
    }

    @CommandHandler
    public void on(ChangeBlogPost changeBlogPost) {
        apply(new BlogPostChanged(
                changeBlogPost.getId(),
                changeBlogPost.getTitle(),
                changeBlogPost.getContent(),
                changeBlogPost.getTags(),
                changeBlogPost.getAuthor()
        ));
    }

    @EventSourcingHandler
    public void blogPostChanged(BlogPostChanged blogPostChanged) {
        this.title = blogPostChanged.getTitle();
        this.content = blogPostChanged.getContent();
        this.tags = blogPostChanged.getTags();
        this.author = blogPostChanged.getAuthor();
    }

    @CommandHandler
    public void delete(DeleteBlogPost deleteBlogPost) {
        apply(new BlogPostDeleted(deleteBlogPost.getId()));
    }

    @EventSourcingHandler
    public void blogPostDeleted(BlogPostDeleted blogPostDeleted) {
        markDeleted();
    }
}
