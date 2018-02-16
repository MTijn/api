package nl.martijnklene.api.application.repository;

import nl.martijnklene.api.application.entity.BlogPost;

public interface BlogPostRepository {
    public void save(BlogPost blogPost);
}
