package nl.martijnklene.api.infrastructure.http.model

import java.time.ZonedDateTime
import javax.validation.constraints.NotEmpty

data class LegacyBlogPost(
    @NotEmpty val title: String,
    @NotEmpty val content: String,
    @NotEmpty val tags: String,
    @NotEmpty val createdAt: ZonedDateTime,
    @NotEmpty val publishedAt: ZonedDateTime
)
