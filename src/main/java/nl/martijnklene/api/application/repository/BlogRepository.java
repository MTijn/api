package nl.martijnklene.api.application.repository;

import nl.martijnklene.api.application.entity.Blog;

import java.util.Collection;

public interface BlogRepository {
    public Collection<Blog> findAll();
}
