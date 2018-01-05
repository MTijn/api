package nl.martijnklene.api.application.entity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Blog {
    private UUID blogId;
    private String title;

    public UUID getBlogId() {
        return blogId;
    }

    public void setBlogId(UUID blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
