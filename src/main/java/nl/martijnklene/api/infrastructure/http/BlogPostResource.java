package nl.martijnklene.api.infrastructure.http;

import io.swagger.annotations.*;
import nl.martijnklene.api.application.command.*;
import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.infrastructure.model.swagger.BlogPayload;
import nl.martijnklene.api.infrastructure.model.swagger.LegacyBlogPost;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.Date;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/blog")
@Api(tags = "Blog")
public class BlogPostResource {
    private CommandGateway commandGateway;
    private BlogPostRepository blogPostRepository;

    public BlogPostResource(
            CommandGateway commandGateway,
            BlogPostRepository blogPostRepository
    ) {
        this.commandGateway = commandGateway;
        this.blogPostRepository = blogPostRepository;
    }

    @ApiResponses(
            @ApiResponse(code = 200, message = "show all items")
    )
    @RequestMapping(
            value = "/all",
            produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity viewAll() {

        return new ResponseEntity<>(blogPostRepository.findAll(), HttpStatus.OK);
    }

    @ApiResponses(
            @ApiResponse(code = 200, message = "Show last published item")
    )
    @RequestMapping(
            value = "/last",
            produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity viewLastPublished() {
        BlogPost blogPost = blogPostRepository.findLastPublished();
        if (blogPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Return limited results"
    )
    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "Show limited amount of blog posts"
            )
    )
    @RequestMapping(
            params = {"from", "limit"},
            produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity viewWithOffSet(
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "1") Integer limit
    ) {
        return ResponseEntity.ok(blogPostRepository.findWithOffset(from, limit));
    }

    @ApiResponses(
            @ApiResponse(
                    code = 201,
                    message = "Blog post created"
            )
    )
    @RequestMapping(
            consumes = APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity create(@Valid @RequestBody BlogPayload blogPayload, Principal principal) {
        UUID id = UUID.randomUUID();

        CreateBlogPost blogPost = new CreateBlogPost(
                id,
                blogPayload.getTitle(),
                blogPayload.getContent(),
                blogPayload.getTags(),
                principal.getName(),
                new Date()
        );

        commandGateway.send(blogPost);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Single blog post"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Blog Post not found"
            )
    })
    @RequestMapping(
            value = "/{blogId}",
            produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<BlogPost> viewSingleBlogPost(@PathVariable UUID blogId) {
        BlogPost blogPost;
        try {
            blogPost = blogPostRepository.findOneById(blogId);
        } catch (Exception noResult) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @ApiResponses(
            @ApiResponse(
                    code = 201,
                    message = "Blog post deleted"
            )
    )
    @RequestMapping(
            value = "/{blogId}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity deleteBlogPost(@PathVariable UUID blogId) {
        BlogPost blogPost;
        try {
            blogPost = blogPostRepository.findOneById(blogId);
        } catch (Exception noResult) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DeleteBlogPost deleteBlogPost = new DeleteBlogPost(blogPost.getId());
        commandGateway.send(deleteBlogPost);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Blog post changed"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Blog post not found"
            )}
    )
    @RequestMapping(
            value = "/{blogId}",
            method = RequestMethod.PUT
    )
    public ResponseEntity changeBlogPost(@PathVariable UUID blogId, @Valid @RequestBody BlogPayload blogPayload, Principal principal) {
        ChangeBlogPost changeBlogPost = new ChangeBlogPost(
                blogId,
                blogPayload.getTitle(),
                blogPayload.getContent(),
                blogPayload.getTags(),
                principal.getName()
        );

        commandGateway.send(changeBlogPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(
                    code = 202,
                    message = "Publishing accepted"
            )
    })
    @RequestMapping(
            value = "/publish/{blogId}",
            method = RequestMethod.PATCH
    )
    public ResponseEntity publishBlogPost(@PathVariable UUID blogId) {
        BlogPost blogPost;
        try {
            blogPost = blogPostRepository.findOneById(blogId);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PublishBlogPost publishBlogPost = new PublishBlogPost(
                blogPost.getId(),
                new Date()
        );

        commandGateway.send(publishBlogPost);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiResponses(
            @ApiResponse(
                    code = 201,
                    message = "Legacy Blog post created"
            )
    )
    @RequestMapping(
            value = "/legacy",
            consumes = APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity postLegacyBlogPost(@Valid @RequestBody LegacyBlogPost legacyBlogPost, Principal principal) {
        UUID id = UUID.randomUUID();

        CreateLegacyBlogPost blogPost = new CreateLegacyBlogPost(
                id,
                legacyBlogPost.getTitle(),
                legacyBlogPost.getContent(),
                legacyBlogPost.getTags(),
                principal.getName(),
                legacyBlogPost.getCreatedAt(),
                legacyBlogPost.getPublishedAt()
        );

        commandGateway.send(blogPost);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
