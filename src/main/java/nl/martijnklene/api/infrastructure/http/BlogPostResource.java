package nl.martijnklene.api.infrastructure.http;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.martijnklene.api.application.command.CreateBlogPost;
import nl.martijnklene.api.infrastructure.model.swagger.BlogPayload;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/blog")
@Api(tags = "Blog")
public class BlogPostResource {
    private CommandGateway commandGateway;

    public BlogPostResource(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
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
    public ResponseEntity create(@RequestBody BlogPayload blogPayload) {
        UUID id = UUID.randomUUID();

        CreateBlogPost blogPost = new CreateBlogPost(
                id,
                blogPayload.getTitle(),
                blogPayload.getContent(),
                blogPayload.getTags(),
                blogPayload.getAuthor()
        );

        commandGateway.send(blogPost);
        return ResponseEntity.ok(id);
    }
}
