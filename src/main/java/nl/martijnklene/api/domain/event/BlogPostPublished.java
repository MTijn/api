package nl.martijnklene.api.domain.event;

import java.util.Date;
import java.util.UUID;

public class BlogPostPublished {
    private final UUID id;
    private final Date date;

    public BlogPostPublished(UUID id, Date date) {
        this.id = id;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
