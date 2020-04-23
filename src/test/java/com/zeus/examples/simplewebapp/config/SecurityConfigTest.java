package com.zeus.examples.simplewebapp.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SecurityConfigTest {

    TestRestTemplate testRestTemplate;
    URL base;
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        testRestTemplate = new TestRestTemplate("user", "password");
        base = new URL("http://localhost:" + port);
    }

    @Test
    public void authorizedUser() throws IllegalStateException, IOException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(base.toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("foodItems"));
    }

    @Test
    public void unauthorizedUser() throws Exception {
        testRestTemplate = new TestRestTemplate("user", "wrongpassword");
        ResponseEntity<String> response = testRestTemplate.getForEntity(base.toString(), String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}