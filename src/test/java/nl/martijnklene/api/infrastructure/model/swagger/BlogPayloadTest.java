package nl.martijnklene.api.infrastructure.model.swagger;

import org.junit.Assert;
import org.junit.Test;

public class BlogPayloadTest {
    private BlogPayload blogPayload = new BlogPayload();

    @Test
    public void getTitle() {
        String title = "title";
        blogPayload.setTitle(title);
        Assert.assertSame(title, blogPayload.getTitle());
    }

    @Test
    public void getContent() {
        String content = "content";
        blogPayload.setContent(content);
        Assert.assertSame(content, blogPayload.getContent());
    }

    @Test
    public void getTags() {
        String tags = "tags";
        blogPayload.setTags(tags);
        Assert.assertSame(tags, blogPayload.getTags());
    }
}
