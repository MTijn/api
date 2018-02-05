package nl.martijnklene.api.infrastructure.configuration;

import com.mongodb.MongoClient;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventStoreConfiguration {
    @Bean
    public MongoEventStorageEngine eventStorageEngine(MongoClient mongoClient) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(mongoClient));
    }
}
