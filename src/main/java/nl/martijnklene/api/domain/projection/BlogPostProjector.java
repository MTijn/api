package nl.martijnklene.api.domain.projection;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.domain.event.*;
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
        blogPost.setCreatedAt(blogPostCreated.getCreatedAt());
        blogPostRepository.save(blogPost);
    }

    @EventHandler
    public void on(LegacyBlogPostCreated legacyBlogPostCreated) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(legacyBlogPostCreated.getId());
        blogPost.setTitle(legacyBlogPostCreated.getTitle());
        blogPost.setContent(legacyBlogPostCreated.getContent());
        blogPost.setTags(legacyBlogPostCreated.getTags());
        blogPost.setAuthor(legacyBlogPostCreated.getAuthor());
        blogPost.setCreatedAt(legacyBlogPostCreated.getCreatedAt());
        blogPost.setPublishedAt(legacyBlogPostCreated.getPublishedAt());
        blogPostRepository.save(blogPost);
    }

    @EventHandler
    public void on(BlogPostChanged blogPostChanged) {
        BlogPost blogPost = blogPostRepository.findOneById(blogPostChanged.getId());
        blogPost.setId(blogPostChanged.getId());
        blogPost.setTitle(blogPostChanged.getTitle());
        blogPost.setContent(blogPostChanged.getContent());
        blogPost.setTags(blogPostChanged.getTags());
        blogPost.setAuthor(blogPostChanged.getAuthor());
        blogPostRepository.save(blogPost);
    }

    @EventHandler
    public void on(BlogPostDeleted blogPostDeleted) {
        BlogPost blogPost = blogPostRepository.findOneById(blogPostDeleted.getId());
        blogPostRepository.delete(blogPost);
    }

    @EventHandler
    public void on(BlogPostPublished blogPostPublished) {
        BlogPost blogPost = blogPostRepository.findOneById(blogPostPublished.getId());
        blogPost.setPublishedAt(blogPostPublished.getDate());
        blogPostRepository.save(blogPost);
    }
}
