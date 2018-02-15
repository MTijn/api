package nl.martijnklene.api.application.commandhandler;

import nl.martijnklene.api.application.command.CreateBlogPostCommand;
import org.axonframework.eventhandling.EventBus;

public class CreateBlogPostCommandHandler {
    private EventBus eventBus;

    public CreateBlogPostCommandHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void on(CreateBlogPostCommand createBlogPostCommand) {
        eventBus.publish();
    }
}
