package nl.martijnklene.api.infrastructure.http;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.martijnklene.api.application.command.ChangeBlogPost;
import nl.martijnklene.api.application.command.CreateBlogPost;
import nl.martijnklene.api.application.command.DeleteBlogPost;
import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.infrastructure.model.swagger.BlogPayload;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/blog")
@Api(tags = "Blog")
public class BlogPostResource {
    private CommandGateway commandGateway;
    private BlogPostRepository blogPostRepository;

    public BlogPostResource(CommandGateway commandGateway, BlogPostRepository blogPostRepository) {
        this.commandGateway = commandGateway;
        this.blogPostRepository = blogPostRepository;
    }

    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "Show all blog posts"
            )
    )
    @RequestMapping(
            produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity viewAll() {
        return ResponseEntity.ok(blogPostRepository.findAll());
    }

    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "Show blog posts with an offset and limit"
            )
    )
    @RequestMapping(
            value = "/{from}/{limit}",
            produces = APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity viewWithOffSet(@PathVariable Integer from, @PathVariable Integer limit) {
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
    public ResponseEntity create(@Valid @RequestBody BlogPayload blogPayload) {
        UUID id = UUID.randomUUID();

        CreateBlogPost blogPost = new CreateBlogPost(
                id,
                blogPayload.getTitle(),
                blogPayload.getContent(),
                blogPayload.getTags(),
                blogPayload.getAuthor()
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
        DeleteBlogPost deleteBlogPost = new DeleteBlogPost(blogId);
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
    public ResponseEntity changeBlogPost(@PathVariable UUID blogId, @Valid @RequestBody BlogPayload blogPayload) {
        ChangeBlogPost changeBlogPost = new ChangeBlogPost(
                blogId,
                blogPayload.getTitle(),
                blogPayload.getContent(),
                blogPayload.getTags(),
                blogPayload.getAuthor()
        );

        commandGateway.send(changeBlogPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
