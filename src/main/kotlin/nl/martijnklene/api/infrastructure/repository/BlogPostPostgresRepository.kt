package nl.martijnklene.api.infrastructure.repository

import nl.martijnklene.api.application.model.BlogPost
import nl.martijnklene.api.application.repository.BlogPostRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Connection
import java.sql.ResultSet
import java.time.ZoneId
import java.util.*

@Repository
class BlogPostPostgresRepository(private val jdbcTemplate: JdbcTemplate) : BlogPostRepository {
    private val selectBlogPostQuery =
        """select id, title, content, tags, author, published_at, created_at from blog_posts""".trimIndent()

    override fun insert(blogPost: BlogPost) {
        jdbcTemplate.update(
            "insert into `blog_post` (`id`, `title`, `content`, `tags`, `author`, `published_at`, `created_at`) " +
                    "values (?, ?, ?, ?, ?, ?)"
        ) {
            it.setString(1, blogPost.id.toString())
            it.setString(2, blogPost.title)
            it.setString(3, blogPost.content)
            it.setString(4, blogPost.tags)
            it.setString(5, blogPost.author)
            it.setInt(6, blogPost.publishedAt!!.toEpochSecond().toInt())
            it.setInt(7, blogPost.createdAt.toEpochSecond().toInt())
        }
    }

    override fun update(blogPost: BlogPost) {
        jdbcTemplate.update(
            "insert update `blog_post` set " +
                    "`title` = ?, " +
                    "`content` = ?, " +
                    "`tags` = ?, " +
                    "`author` = ?, " +
                    "`published_at` = ?, " +
                    "`created_at` = ? " +
                    "where id = ?"
        ) {
            it.setString(1, blogPost.title)
            it.setString(2, blogPost.content)
            it.setString(3, blogPost.tags)
            it.setString(4, blogPost.author)
            it.setInt(5, blogPost.publishedAt!!.toEpochSecond().toInt())
            it.setInt(6, blogPost.createdAt.toEpochSecond().toInt())
            it.setString(7, blogPost.id.toString())
        }
    }

    override fun deleteById(id: UUID) {
        jdbcTemplate.update(
            "delete from `blog_post` where `id` = ?"
        ) { id.toString() }
    }

    override fun findOneById(id: UUID): BlogPost? {
        return jdbcTemplate.query({ connection: Connection ->
            val statement = connection.prepareStatement(
                "$selectBlogPostQuery where id = ?"
            )
            statement.setString(1, id.toString())
            statement
        }) { resultSet: ResultSet, _ ->
            mapBlogPostByResultSet(resultSet)
        }.firstOrNull()
    }

    override fun findByTitle(title: String): Collection<BlogPost> {
        return jdbcTemplate.query({ connection: Connection ->
            val statement = connection.prepareStatement(
                "$selectBlogPostQuery where title = ?"
            )
            statement.setString(1, title)
            statement
        }) { resultSet: ResultSet, _ ->
            mapBlogPostByResultSet(resultSet)
        }
    }

    override fun findAll(): Collection<BlogPost> {
        return jdbcTemplate.query("$selectBlogPostQuery order by created_at desc limit 1") { resultSet: ResultSet, _ ->
            mapBlogPostByResultSet(resultSet)
        }
    }

    override fun findWithOffset(from: Int, limit: Int): Collection<BlogPost> {
        return jdbcTemplate.query({ connection: Connection ->
            val statement = connection.prepareStatement(
                "$selectBlogPostQuery limit ?, ?"
            )
            statement.setInt(1, from)
            statement.setInt(2, limit)
            statement
        }) { resultSet: ResultSet, _ ->
            mapBlogPostByResultSet(resultSet)
        }
    }

    override fun findLastPublished(): BlogPost? {
        return jdbcTemplate.query("$selectBlogPostQuery order by created_at desc limit 1") { resultSet: ResultSet, _ ->
            mapBlogPostByResultSet(resultSet)
        }.firstOrNull()
    }

    fun mapBlogPostByResultSet(resultSet: ResultSet): BlogPost {
        return BlogPost(
            UUID.fromString(resultSet.getString("id")),
            resultSet.getString("title"),
            resultSet.getString("content"),
            resultSet.getString("tags"),
            resultSet.getString("author"),
            resultSet.getTimestamp("published_at").toLocalDateTime().atZone(ZoneId.of("UTC")) ?: null,
            resultSet.getTimestamp("created_at").toLocalDateTime().atZone(ZoneId.of("UTC"))
        )
    }
}
