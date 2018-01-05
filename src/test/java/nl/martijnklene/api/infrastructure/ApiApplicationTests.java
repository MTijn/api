package nl.martijnklene.api.infrastructure;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(
        classes = {ApiApplication.class}
)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class ApiApplicationTests {

}
