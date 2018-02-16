package nl.martijnklene.api.domain.projection;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.domain.event.BlogPostCreated;
import nl.martijnklene.api.domain.event.BlogPostDeleted;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("BlogPost")
public class BlogPostProjector {
    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostProjector(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @EventHandler
    public void on(BlogPostCreated blogPostCreated) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(blogPostCreated.getId());
        blogPost.setTitle(blogPostCreated.getTitle());
        blogPost.setContent(blogPostCreated.getContent());
        blogPost.setTags(blogPostCreated.getTags());
        blogPost.setAuthor(blogPostCreated.getAuthor());
        blogPostRepository.save(blogPost);
    }

    @EventHandler
    public void on(BlogPostDeleted blogPostDeleted) {
        BlogPost blogPost = blogPostRepository.findOneById(blogPostDeleted.getId());
        blogPostRepository.delete(blogPost);
    }
}
