package nl.martijnklene.api.application.command;

import java.util.UUID;

public class CreateBlogCommand {
    private final UUID blogId;
    private final String title;

    public CreateBlogCommand(UUID blogId, String title) {
        this.blogId = blogId;
        this.title = title;
    }

    public UUID getBlogId() {
        return blogId;
    }

    public String getTitle() {
        return title;
    }
}
