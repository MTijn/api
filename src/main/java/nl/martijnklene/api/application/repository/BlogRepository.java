package nl.martijnklene.api.application.repository;

import nl.martijnklene.api.application.entity.Blog;

import java.util.Collection;
import java.util.UUID;

public interface BlogRepository {
    public void save(Blog blog);
    public Collection<Blog> findAll();
    public Blog findOneById(UUID id);
}
