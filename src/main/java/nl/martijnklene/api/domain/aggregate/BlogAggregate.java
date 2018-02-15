package nl.martijnklene.api.domain.aggregate;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.UUID;

@Aggregate
public class BlogAggregate implements Serializable {
    @AggregateIdentifier
    private UUID id;

    private String title;

    public BlogAggregate(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
