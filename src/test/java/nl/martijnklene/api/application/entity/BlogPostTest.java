package nl.martijnklene.api.application.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class BlogPostTest {

    private BlogPost blogPost = new BlogPost();

    @Test
    public void getId() {
        UUID id = UUID.randomUUID();
        blogPost.setId(id);
        Assert.assertSame(id, blogPost.getId());
    }

    @Test
    public void getTitle() {
        String title = "title";
        blogPost.setTitle(title);
        Assert.assertSame(title, blogPost.getTitle());
    }

    @Test
    public void getContent() {
        String content = "content";
        blogPost.setContent(content);
        Assert.assertSame(content, blogPost.getContent());
    }

    @Test
    public void getTags() {
        String tags = "tags";
        blogPost.setTags(tags);
        Assert.assertSame(tags, blogPost.getTags());
    }

    @Test
    public void getAuthor() {
        String author = "author";
        blogPost.setAuthor(author);
        Assert.assertSame(author, blogPost.getAuthor());
    }
}