package nl.martijnklene.api.infrastructure.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CommandBus {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void handle(Object object) {
        publisher.publishEvent(object);
    }
}
