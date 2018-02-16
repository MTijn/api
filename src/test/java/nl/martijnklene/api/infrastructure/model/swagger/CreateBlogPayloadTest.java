package nl.martijnklene.api.infrastructure.model.swagger;

import org.junit.Assert;
import org.junit.Test;

public class CreateBlogPayloadTest {
    private CreateBlogPayload createBlogPayload = new CreateBlogPayload();

    @Test
    public void getTitle() {
        String title = "title";
        createBlogPayload.setTitle(title);
        Assert.assertSame(title, createBlogPayload.getTitle());
    }

    @Test
    public void getContent() {
        String content = "content";
        createBlogPayload.setContent(content);
        Assert.assertSame(content, createBlogPayload.getContent());
    }

    @Test
    public void getTags() {
        String tags = "tags";
        createBlogPayload.setTags(tags);
        Assert.assertSame(tags, createBlogPayload.getTags());
    }

    @Test
    public void getAuthor() {
        String author = "author";
        createBlogPayload.setAuthor(author);
        Assert.assertSame(author, createBlogPayload.getAuthor());
    }
}
