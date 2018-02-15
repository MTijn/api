package nl.martijnklene.api.application.repository;

import nl.martijnklene.api.application.entity.BlogPost;

import java.util.Collection;
import java.util.UUID;

public interface BlogPostRepository {
    public BlogPost findOne(UUID id);
    public Collection<BlogPost> findAll();
    public void save(BlogPost blogPost);
}
