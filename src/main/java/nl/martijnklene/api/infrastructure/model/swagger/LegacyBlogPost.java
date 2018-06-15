package nl.martijnklene.api.infrastructure.model.swagger;

import java.util.Date;

public class LegacyBlogPost extends BlogPayload {
    private Date createdAt;
    private Date publishedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
