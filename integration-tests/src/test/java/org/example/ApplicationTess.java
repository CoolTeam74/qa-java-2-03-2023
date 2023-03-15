package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTess extends BaseIT {
    @Autowired
    TestService testService;

    @LocalServerPort
    Integer port;

    @Test
    void testApplication() {
        System.out.println(port);
        assertNotNull(port);
        assertNotNull(testService);
    }
}
