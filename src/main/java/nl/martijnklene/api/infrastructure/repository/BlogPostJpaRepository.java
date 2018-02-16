package nl.martijnklene.api.infrastructure.repository;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlogPostJpaRepository implements BlogPostRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public void save(BlogPost blogPost) {
        entityManager.merge(blogPost);
    }
}
