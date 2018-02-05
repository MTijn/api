package nl.martijnklene.api.infrastructure.http;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.martijnklene.api.application.command.CreateBlogCommand;
import nl.martijnklene.api.application.repository.BlogRepository;
import nl.martijnklene.api.infrastructure.model.swagger.BlogPayload;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping(value = "/v1/blog")
@Api(tags = "blog")
public class BlogResource {
    private BlogRepository blogRepository;
    private CommandGateway commandGateway;

    @Autowired
    public BlogResource(BlogRepository blogRepository, CommandGateway commandGateway) {
        this.blogRepository = blogRepository;
        this.commandGateway = commandGateway;
    }

    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "Blog items"
            )
    )
    @RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET
    )
    public ResponseEntity overview() {
        return ResponseEntity.ok(this.blogRepository.findAll());
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
    public ResponseEntity create(@RequestBody BlogPayload blogPayload) {
        UUID blogId = UUID.randomUUID();
        CreateBlogCommand createBlogCommand = new CreateBlogCommand(
                blogId,
                blogPayload.getTitle()
        );

        commandGateway.send(createBlogCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(blogId.toString())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
