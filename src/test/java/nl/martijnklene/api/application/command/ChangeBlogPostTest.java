package nl.martijnklene.api.application.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ChangeBlogPostTest {

    @Test
    public void getCreationOfChange() {
        final UUID testUuid = UUID.randomUUID();
        final String title = "title";
        final String content = "content";
        final String tags = "tags";
        final String author = "author";

        ChangeBlogPost changeBlogPost = new ChangeBlogPost(
                testUuid,
                title,
                content,
                tags,
                author
        );

        Assert.assertSame(testUuid, changeBlogPost.getId());
        Assert.assertSame(title, changeBlogPost.getTitle());
        Assert.assertSame(content, changeBlogPost.getContent());
        Assert.assertSame(tags, changeBlogPost.getTags());
        Assert.assertSame(author, changeBlogPost.getAuthor());
    }
}