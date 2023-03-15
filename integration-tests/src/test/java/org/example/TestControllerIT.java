package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControllerIT extends BaseIT {
    @Test
    @Sql({"/data/clearAll.sql", "/data/testData.sql"})
    public void testGetAllDataSuccessful() {
        HttpEntity request = new HttpEntity(null, new HttpHeaders());
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "/api/tests", HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<User> body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.size());
        assertEquals(123, body.get(0).getId());
    }
}
