package nl.martijnklene.api.infrastructure.repository;

import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.UUID;

@Repository
public class BlogPostJpaRepository implements BlogPostRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public void save(BlogPost blogPost) {
        entityManager.merge(blogPost);
    }

    @Override
    public BlogPost findOneById(UUID id) {
        return entityManager.createQuery("SELECT b FROM BlogPost b WHERE id = :blogId", BlogPost.class)
                .setParameter("blogId", id)
                .getSingleResult();
    }

    @Override
    public Collection<BlogPost> findAll() {
        return entityManager.createQuery("SELECT b FROM BlogPost b", BlogPost.class).getResultList();
    }
}
