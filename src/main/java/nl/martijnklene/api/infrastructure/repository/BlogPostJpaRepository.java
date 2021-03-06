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

    @Override
    public void save(BlogPost blogPost) {
        entityManager.merge(blogPost);
    }

    @Override
    public void delete(BlogPost blogPost) {
        entityManager.remove(blogPost);
    }

    @Override
    public BlogPost findOneById(UUID id) {
        return entityManager.createQuery("SELECT b FROM BlogPost b WHERE id = :blogId", BlogPost.class)
                .setParameter("blogId", id)
                .getSingleResult();
    }

    @Override
    public Collection<BlogPost> findByTitle(String title) {
        return entityManager.createQuery("SELECT b FROM BlogPost b WHERE title = :blogTitle", BlogPost.class)
                .setParameter("blogTitle", title)
                .getResultList();
    }

    @Override
    public Collection<BlogPost> findAll() {
        return entityManager.createQuery("SELECT b FROM BlogPost b ORDER BY b.createdAt DESC", BlogPost.class)
                .getResultList();
    }

    public Collection<BlogPost> findWithOffset(Integer from, Integer limit) {
        return entityManager.createQuery("SELECT b FROM BlogPost b ORDER BY b.createdAt", BlogPost.class)
                .setFirstResult(from)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public BlogPost findLastPublished() {
        return entityManager.createQuery("SELECT b FROM BlogPost b WHERE b.publishedAt IS NOT NULL ORDER BY b.publishedAt DESC", BlogPost.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}
