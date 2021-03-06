package nl.martijnklene.api.infrastructure.model.swagger;

import nl.martijnklene.api.infrastructure.annotation.UniqueTitle;

import javax.validation.constraints.NotEmpty;

public class BlogPayload {
    @UniqueTitle
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
