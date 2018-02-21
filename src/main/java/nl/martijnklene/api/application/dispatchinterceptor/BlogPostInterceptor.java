package nl.martijnklene.api.application.dispatchinterceptor;

import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

public class BlogPostInterceptor implements MessageDispatchInterceptor {
    @Override
    public BiFunction handle(List list) {
        return null;
    }

    @Override
    public Message<?> handle(Message message) {
        return null;
    }
}
