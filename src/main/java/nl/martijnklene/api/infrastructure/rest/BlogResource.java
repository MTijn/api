package nl.martijnklene.api.infrastructure.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.martijnklene.api.application.command.CreateBlogPostCommand;
import nl.martijnklene.api.infrastructure.model.swagger.CreateBlogPost;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping(value = "/v1/blog")
@Api(tags = "blog")
public class BlogResource {
    private CommandGateway commandGateway;

    @Autowired
    public BlogResource(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }



    @ApiResponses(
            @ApiResponse(
                    code = 201,
                    message = "Blog creation"
            )
    )
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity create(@RequestBody CreateBlogPost blogPayload) {
        UUID blogId = UUID.randomUUID();
        CreateBlogPostCommand createBlogPostCommand = new CreateBlogPostCommand(
                blogId,
                blogPayload.getTitle(),
                blogPayload.getContent(),
                blogPayload.getTags(),
                blogPayload.getAuthor()
        );

        commandGateway.send(createBlogPostCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(blogId.toString())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
