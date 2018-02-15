package nl.martijnklene.api.infrastructure.repository;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.UUID;

@Repository
public class BlogPostJpaRepository implements BlogPostRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public BlogPost findOne(UUID id) {
        return null;
    }

    @Override
    public Collection<BlogPost> findAll() {
        return null;
    }

    @Override
    public void save(BlogPost blogPost) {
        entityManager.merge(blogPost);
    }
}
