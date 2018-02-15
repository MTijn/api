package nl.martijnklene.api.infrastructure.configuration;

import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {
    @Bean
    public InMemoryEventStorageEngine eventStore() {
        return new InMemoryEventStorageEngine();
    }
}
