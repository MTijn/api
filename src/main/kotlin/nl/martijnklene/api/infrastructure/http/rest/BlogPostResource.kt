package nl.martijnklene.api.infrastructure.http.rest

import io.swagger.annotations.*
import nl.martijnklene.api.application.model.BlogPost
import nl.martijnklene.api.application.repository.BlogPostRepository
import nl.martijnklene.api.infrastructure.http.model.BlogPayload
import nl.martijnklene.api.infrastructure.http.model.LegacyBlogPost
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.security.Principal
import java.time.ZonedDateTime
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/v1/blog"])
@Api("Blog")
class BlogPostResource(
    private val blogPostRepository: BlogPostRepository
) {
    @ApiResponses(ApiResponse(code = 200, message = "show all items"))
    @RequestMapping(
        value = ["/all"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.GET]
    )
    fun viewAll(): ResponseEntity<*> {
        return ResponseEntity(blogPostRepository.findAll(), HttpStatus.OK)
    }

    @ApiResponses(ApiResponse(code = 200, message = "Show last published item"))
    @RequestMapping(
        value = ["/last"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.GET]
    )
    fun viewLastPublished(): ResponseEntity<*> {
        val blogPost = blogPostRepository.findLastPublished() ?: return ResponseEntity<Any>(HttpStatus.NOT_FOUND)
        return ResponseEntity<Any>(blogPost, HttpStatus.OK)
    }

    @ApiOperation(value = "Return limited results")
    @ApiResponses(ApiResponse(code = 200, message = "Show limited amount of blog posts"))
    @RequestMapping(
        params = ["from", "limit"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.GET]
    )
    fun viewWithOffSet(
        @RequestParam(defaultValue = "0") from: Int?,
        @RequestParam(defaultValue = "1") limit: Int?
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            blogPostRepository.findWithOffset(
                from!!,
                limit!!
            )
        )
    }

    @ApiResponses(ApiResponse(code = 201, message = "Blog post created"))
    @RequestMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.POST]
    )
    fun create(
        @RequestBody blogPayload: @Valid BlogPayload,
        principal: Principal
    ): ResponseEntity<Any> {
        val id = UUID.randomUUID()
        val blogPost = BlogPost(
            id,
            blogPayload.title,
            blogPayload.content,
            blogPayload.tags,
            principal.name,
            null,
            ZonedDateTime.now()
        )
        blogPostRepository.insert(blogPost)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .buildAndExpand(id)
            .toUri()
        return ResponseEntity.created(location).build()
    }

    @ApiResponses(
        ApiResponse(code = 200, message = "Single blog post"),
        ApiResponse(code = 404, message = "Blog Post not found")
    )
    @RequestMapping(
        value = ["/{blogId}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.GET]
    )
    fun viewSingleBlogPost(@PathVariable blogId: UUID): ResponseEntity<BlogPost> {
        val blogPost = blogPostRepository.findOneById(blogId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity(blogPost, HttpStatus.OK)
    }

    @ApiResponses(ApiResponse(code = 201, message = "Blog post deleted"))
    @RequestMapping(value = ["/{blogId}"], method = [RequestMethod.DELETE])
    fun deleteBlogPost(@PathVariable blogId: UUID): ResponseEntity<Any> {
        val blogPost = blogPostRepository.findOneById(blogId) ?: return ResponseEntity.notFound().build()
        blogPostRepository.deleteById(blogId)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @ApiResponses(
        ApiResponse(code = 201, message = "Blog post changed"),
        ApiResponse(code = 404, message = "Blog post not found")
    )
    @RequestMapping(value = ["/{blogId}"], method = [RequestMethod.PUT])
    fun changeBlogPost(
        @PathVariable blogId: UUID,
        @RequestBody blogPayload: @Valid BlogPayload,
        principal: Principal
    ): ResponseEntity<Any> {
        val blogPost = blogPostRepository.findOneById(blogId) ?: return ResponseEntity.notFound().build()
        blogPost.title = blogPayload.title
        blogPost.content = blogPayload.content
        blogPost.tags = blogPayload.tags

        blogPostRepository.insert(blogPost)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @ApiResponses(ApiResponse(code = 202, message = "Publishing accepted"))
    @RequestMapping(value = ["/publish/{blogId}"], method = [RequestMethod.PATCH])
    fun publishBlogPost(@PathVariable blogId: UUID): ResponseEntity<Any> {
        val blogPost = blogPostRepository.findOneById(blogId) ?: return ResponseEntity.notFound().build()
        blogPost.publishedAt = ZonedDateTime.now()
        blogPostRepository.insert(blogPost)
        return ResponseEntity<Any>(HttpStatus.ACCEPTED)
    }

    @ApiResponses(ApiResponse(code = 201, message = "Legacy Blog post created"))
    @RequestMapping(
        value = ["/legacy"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.POST]
    )
    fun postLegacyBlogPost(
        @RequestBody legacyBlogPost: @Valid LegacyBlogPost,
        principal: Principal
    ): ResponseEntity<*> {
        val id = UUID.randomUUID()
        val blogPost = BlogPost(
            id,
            legacyBlogPost.title,
            legacyBlogPost.content,
            legacyBlogPost.tags,
            principal.name,
            legacyBlogPost.createdAt,
            legacyBlogPost.publishedAt
        )

        blogPostRepository.insert(blogPost)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .buildAndExpand(id)
            .toUri()
        return ResponseEntity.created(location).build<Any>()
    }
}
