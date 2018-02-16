package nl.martijnklene.api.application.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class CreateBlogPostTest {
    private final UUID id = UUID.randomUUID();
    private final String title = "test";
    private final String content = "content";
    private final String tags = "tags";
    private final String author = "author";

    private final CreateBlogPost createBlogPost = new CreateBlogPost(
            id,
            title,
            content,
            tags,
            author
    );
    @Test
    public void testCreatingBlogPosts() {
        Assert.assertSame(id, createBlogPost.getId());
        Assert.assertSame(title, createBlogPost.getTitle());
        Assert.assertSame(content, createBlogPost.getContent());
        Assert.assertSame(tags, createBlogPost.getTags());
        Assert.assertSame(author, createBlogPost.getAuthor());
    }
}
