package nl.martijnklene.api.infrastructure;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/integration-cucumber.json"},
        features = "src/test/resources/features"
)
public class CucumberIntegration {
}
