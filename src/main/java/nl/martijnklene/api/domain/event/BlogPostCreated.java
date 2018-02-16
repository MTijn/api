package nl.martijnklene.api.domain.event;

import java.util.UUID;

public class BlogPostCreated {
    private UUID id;
    private String title;
    private String content;
    private String tags;
    private String author;

    public BlogPostCreated(UUID id, String title, String content, String tags, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTags() {
        return tags;
    }

    public String getAuthor() {
        return author;
    }
}
