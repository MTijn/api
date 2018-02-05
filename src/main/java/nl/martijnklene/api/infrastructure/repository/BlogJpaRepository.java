package nl.martijnklene.api.infrastructure.repository;

import nl.martijnklene.api.application.entity.Blog;
import nl.martijnklene.api.application.repository.BlogRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class BlogJpaRepository implements BlogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Blog blog) {
        entityManager.merge(blog);
    }

    public Collection<Blog> findAll() {
        return entityManager.createQuery("SELECT b FROM Blog b", Blog.class).getResultList();
    }
}
