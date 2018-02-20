package nl.martijnklene.api.application.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class DeleteBlogPostTest {

    @Test
    public void getId() {
        final UUID testUuid = UUID.randomUUID();
        DeleteBlogPost deleteBlogPost = new DeleteBlogPost(testUuid);
        Assert.assertSame(testUuid, deleteBlogPost.getId());
    }
}