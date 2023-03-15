package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = MyAppApplication.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("it")
public abstract class BaseIT {
    @Autowired
    protected TestRestTemplate restTemplate;

    private static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = (MySQLContainer) new MySQLContainer("mysql:8.0")
                .withUsername("test")
                .withPassword("test")
                .withReuse(true);
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }
}
