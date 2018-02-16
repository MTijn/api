package nl.martijnklene.api.domain.eventHandler;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.domain.event.BlogPostCreated;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogPostEventHandler {
    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostEventHandler(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @EventSourcingHandler
    public void on(BlogPostCreated blogPostCreated) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(blogPostCreated.getId());
        blogPost.setTitle(blogPostCreated.getTitle());
        blogPost.setContent(blogPostCreated.getContent());
        blogPost.setTags(blogPostCreated.getTags());
        blogPost.setAuthor(blogPostCreated.getAuthor());

        blogPostRepository.save(blogPost);
    }
}
