package nl.martijnklene.api.domain.aggregate;

import nl.martijnklene.api.application.command.CreateBlogCommand;
import nl.martijnklene.api.application.entity.Blog;
import nl.martijnklene.api.application.repository.BlogRepository;
import nl.martijnklene.api.domain.event.BlogCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;

@Aggregate
public class BlogAggregate implements Serializable {
    @AggregateIdentifier
    private UUID id;

    private String title;

}
