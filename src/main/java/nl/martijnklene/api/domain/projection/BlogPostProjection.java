package nl.martijnklene.api.domain.projection;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.domain.event.BlogPostCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class BlogPostProjection {
    private BlogPostRepository blogPostRepository;

    public BlogPostProjection(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @EventHandler
    public void handle(BlogPostCreatedEvent blogPostCreatedEvent) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(blogPostCreatedEvent.getId());
        blogPost.setTitle(blogPostCreatedEvent.getTitle());
        blogPost.setContent(blogPostCreatedEvent.getContent());
        blogPost.setTags(blogPostCreatedEvent.getTags());
        blogPost.setAuthor(blogPostCreatedEvent.getAuthor());
        blogPostRepository.save(blogPost);
    }
}
