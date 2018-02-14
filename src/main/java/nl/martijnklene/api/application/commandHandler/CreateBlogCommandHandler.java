package nl.martijnklene.api.application.commandHandler;

import nl.martijnklene.api.application.command.CreateBlogCommand;
import nl.martijnklene.api.domain.event.BlogCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericEventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBlogCommandHandler {
    private EventBus eventBus;

    @Autowired
    public CreateBlogCommandHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @CommandHandler
    public void handle(CreateBlogCommand command) {
        EventMessage<BlogCreatedEvent> blogCreatedEvent = GenericEventMessage.asEventMessage(new BlogCreatedEvent(
                command.getId(),
                command.getTitle()
        ));
        eventBus.publish(blogCreatedEvent);
    }
}
