package nl.martijnklene.api.application.repository;

import nl.martijnklene.api.application.entity.BlogPost;

import java.util.Collection;
import java.util.UUID;

public interface BlogPostRepository {
    void save(BlogPost blogPost);
    BlogPost findOneById(UUID id);
    BlogPost findOneByTitle(String title);
    Collection<BlogPost> findAll();
    void delete(BlogPost blogPost);
}
