package nl.martijnklene.api.infrastructure.configuration;

import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {
    @Bean
    @Qualifier("localSegment")
    public CommandBus localSegment(TransactionManager transactionManager) {
        AsynchronousCommandBus asynchronousCommandBus = new AsynchronousCommandBus();
        asynchronousCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
        asynchronousCommandBus.registerHandlerInterceptor(new TransactionManagingInterceptor<>(transactionManager));

        return asynchronousCommandBus;
    }
}
