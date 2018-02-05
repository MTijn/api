package nl.martijnklene.api.domain.projection;

import nl.martijnklene.api.application.entity.Blog;
import nl.martijnklene.api.application.repository.BlogRepository;
import nl.martijnklene.api.domain.event.BlogCreatedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;

@Component
public class BlogProjection {
    private BlogRepository blogRepository;

    public BlogProjection(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @EventSourcingHandler
    public void onCreationEvent(BlogCreatedEvent blogCreatedEvent) {
        Blog blog = new Blog();
        blog.setBlogId(blogCreatedEvent.getId());
        blog.setTitle(blogCreatedEvent.getTitle());
        this.blogRepository.save(blog);
    }
}
