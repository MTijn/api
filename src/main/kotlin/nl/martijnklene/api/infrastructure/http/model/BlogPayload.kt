package nl.martijnklene.api.infrastructure.http.model

import javax.validation.constraints.NotEmpty

data class BlogPayload (
    @NotEmpty val title: String,
    @NotEmpty val content: String,
    @NotEmpty val tags: String
)
