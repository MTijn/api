package nl.martijnklene.api.infrastructure.glue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.martijnklene.api.application.command.CreateBlogPost;
import nl.martijnklene.api.application.entity.BlogPost;
import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.infrastructure.SpringIntegrationTest;
import nl.martijnklene.api.infrastructure.model.swagger.BlogPayload;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateBlogPostSteps extends SpringIntegrationTest {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private BlogPostRepository blogPostRepository;

    private final UUID generatedUuid = UUID.randomUUID();
    private final Date createdDate = new Date();

    @When("I submit my request with the following content:$")
    public void submitRequest(List<BlogPayload> blogPayloads) {
        for (BlogPayload blogPayload: blogPayloads) {
            CreateBlogPost createBlogPost = new CreateBlogPost(
                    generatedUuid,
                    blogPayload.getTitle(),
                    blogPayload.getContent(),
                    blogPayload.getTags(),
                    "test@test.nl",
                    createdDate
            );
            commandGateway.send(createBlogPost).join();
        }
    }

    @Then("a new blog post should have been created")
    public void blogPostCreated() {
        BlogPost blogPost = blogPostRepository.findOneById(generatedUuid);
        Assert.assertEquals(generatedUuid, blogPost.getId());
    }

    @And("the creation date should be of today")
    public void creationDateMustBeToday() {
        BlogPost blogPost = blogPostRepository.findOneById(generatedUuid);
        Assert.assertEquals(createdDate, blogPost.getCreatedAt());
    }
}
