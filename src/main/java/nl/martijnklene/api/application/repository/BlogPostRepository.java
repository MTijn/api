package nl.martijnklene.api.application.repository;

import nl.martijnklene.api.application.entity.BlogPost;

import java.util.Collection;
import java.util.UUID;

public interface BlogPostRepository {
    void save(BlogPost blogPost);
    BlogPost findOneById(UUID id);
    Collection<BlogPost> findAll();
}
