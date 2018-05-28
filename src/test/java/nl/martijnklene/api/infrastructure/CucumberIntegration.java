package nl.martijnklene.api.infrastructure;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/integration-cucumber.json"},
        features = "src/test/resources/features"
)
@SpringBootTest
public class CucumberIntegration {
}
