package nl.martijnklene.api.infrastructure.glue;
import cucumber.api.java.Before;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseSteps {
    @Autowired
    private Flyway flyway;

    @Before
    public void clearDatabase() {
        flyway.setValidateOnMigrate(false);
        flyway.setGroup(true);

        flyway.clean();
        flyway.migrate();
    }
}
