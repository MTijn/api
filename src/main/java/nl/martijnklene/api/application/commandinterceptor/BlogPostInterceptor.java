package nl.martijnklene.api.application.commandinterceptor;

import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;

public class BlogPostInterceptor implements MessageHandlerInterceptor {
    @Override
    public Object handle(UnitOfWork unitOfWork, InterceptorChain interceptorChain) throws Exception {
        return null;
    }
}
