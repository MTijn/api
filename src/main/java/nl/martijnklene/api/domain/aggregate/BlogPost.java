package nl.martijnklene.api.domain.aggregate;

import lombok.NoArgsConstructor;
import nl.martijnklene.api.application.command.*;
import nl.martijnklene.api.domain.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.Date;
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
    private Date publishedAt;
    private Date createdAt;

    @SuppressWarnings("all")
    @CommandHandler
    public BlogPost(CreateBlogPost createBlogPost) {
        apply(new BlogPostCreated(
                createBlogPost.getId(),
                createBlogPost.getTitle(),
                createBlogPost.getContent(),
                createBlogPost.getTags(),
                createBlogPost.getAuthor(),
                createBlogPost.getCreatedAt()
        ));
    }

    @SuppressWarnings("all")
    @CommandHandler
    public BlogPost(CreateLegacyBlogPost createBlogPost) {
        apply(new LegacyBlogPostCreated(
                createBlogPost.getId(),
                createBlogPost.getTitle(),
                createBlogPost.getContent(),
                createBlogPost.getTags(),
                createBlogPost.getAuthor(),
                createBlogPost.getCreatedAt(),
                createBlogPost.getPublishedAt()
        ));
    }

    @EventSourcingHandler
    public void blogPostCreated(BlogPostCreated blogPostCreated) {
        this.id = blogPostCreated.getId();
        this.title = blogPostCreated.getTitle();
        this.content = blogPostCreated.getContent();
        this.tags = blogPostCreated.getTags();
        this.author = blogPostCreated.getAuthor();
        this.createdAt = blogPostCreated.getCreatedAt();
    }

    @EventSourcingHandler
    public void legacyBlogPostCreated(LegacyBlogPostCreated legacyBlogPostCreated) {
        this.id = legacyBlogPostCreated.getId();
        this.title = legacyBlogPostCreated.getTitle();
        this.content = legacyBlogPostCreated.getContent();
        this.tags = legacyBlogPostCreated.getTags();
        this.author = legacyBlogPostCreated.getAuthor();
        this.createdAt = legacyBlogPostCreated.getCreatedAt();
        this.publishedAt = legacyBlogPostCreated.getPublishedAt();
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

    @CommandHandler
    public void publish(PublishBlogPost publishBlogPost) {
        apply(new BlogPostPublished(publishBlogPost.getId(), publishBlogPost.getPublishedAt()));
    }

    @EventSourcingHandler
    public void publish(BlogPostPublished blogPostPublished) {
        this.publishedAt = blogPostPublished.getDate();
    }
}
