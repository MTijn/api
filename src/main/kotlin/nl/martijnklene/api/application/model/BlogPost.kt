package nl.martijnklene.api.application.model

import java.time.ZonedDateTime
import java.util.*

data class BlogPost(
    var id: UUID,
    var title: String,
    var content: String,
    var tags: String?,
    var author: String,
    var publishedAt: ZonedDateTime?,
    var createdAt: ZonedDateTime
)
