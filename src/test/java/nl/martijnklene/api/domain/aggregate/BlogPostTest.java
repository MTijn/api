package nl.martijnklene.api.domain.aggregate;

import nl.martijnklene.api.application.command.ChangeBlogPost;
import nl.martijnklene.api.application.command.CreateBlogPost;
import nl.martijnklene.api.application.command.DeleteBlogPost;
import nl.martijnklene.api.application.command.PublishBlogPost;
import nl.martijnklene.api.domain.event.BlogPostChanged;
import nl.martijnklene.api.domain.event.BlogPostCreated;
import nl.martijnklene.api.domain.event.BlogPostDeleted;
import nl.martijnklene.api.domain.event.BlogPostPublished;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class BlogPostTest {
    private FixtureConfiguration<BlogPost> fixture;

    private final UUID createdId = UUID.randomUUID();
    private final String title = "title";
    private final String content = "content";
    private final String tags = "tags";
    private final String author = "test@test.nl";
    private final Date date = new Date();

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(BlogPost.class);
    }

    @Test
    public void testCreateBlogPost() {
        fixture.given()
            .when(new CreateBlogPost(
                createdId,
                title,
                content,
                tags,
                author,
                date
            )).expectSuccessfulHandlerExecution()
            .expectEvents(new BlogPostCreated(
                createdId,
                title,
                content,
                tags,
                author,
                date
            )
        );
    }

    @Test
    public void testChangeBlogPost() {
        fixture
            .given(new BlogPostCreated(
                createdId,
                title,
                content,
                tags,
                author,
                date
            )).when(new ChangeBlogPost(
                createdId,
                title + " 2" ,
                content,
                tags,
                author
            )).expectSuccessfulHandlerExecution()
            .expectEvents(new BlogPostChanged(
                createdId,
                title + " 2" ,
                content,
                tags,
                author
            ));
    }

    @Test
    public void testDeleteBlogPost() {
        fixture
            .given(new BlogPostCreated(
                createdId,
                title,
                content,
                tags,
                author,
                date
            ))
            .when(new DeleteBlogPost(createdId))
            .expectSuccessfulHandlerExecution()
            .expectEvents(new BlogPostDeleted(createdId));
    }

    @Test
    public void testPublishBlogPost() {
        fixture
            .given(new BlogPostCreated(
                createdId,
                title,
                content,
                tags,
                author,
                date
            ))
            .when(new PublishBlogPost(createdId, date))
            .expectSuccessfulHandlerExecution()
            .expectEvents(new BlogPostPublished(createdId, date));
    }
}
