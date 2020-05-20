package nl.martijnklene.api.application.repository

import nl.martijnklene.api.application.model.BlogPost
import java.util.*

interface BlogPostRepository {
    fun insert(blogPost: BlogPost)
    fun update(blogPost: BlogPost)
    fun deleteById(id: UUID)
    fun findOneById(id: UUID): BlogPost?
    fun findByTitle(title: String): Collection<BlogPost>
    fun findAll(): Collection<BlogPost>
    fun findWithOffset(from: Int, limit: Int): Collection<BlogPost>
    fun findLastPublished(): BlogPost?
}
