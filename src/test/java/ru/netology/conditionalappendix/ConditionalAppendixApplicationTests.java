package ru.netology.conditionalappendix;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalAppendixApplicationTests {
    private static final String DEV_RESP = "Current profile is dev";
    private static final String PROD_RESP = "Current profile is production";

    @Autowired
    TestRestTemplate restTemplate;

    private static GenericContainer<?> myapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static GenericContainer<?> myapp2 = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myapp.start();
        myapp2.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forDevApp = restTemplate.getForEntity("http://localhost:" + myapp.getMappedPort(8080) + "/profile", String.class);
        final String devBody = forDevApp.getBody();
        System.out.println(devBody);
        assertEquals(DEV_RESP, devBody);

        ResponseEntity<String> forProdApp = restTemplate.getForEntity("http://localhost:" + myapp2.getMappedPort(8081) + "/profile", String.class);
        final String prodBody = forProdApp.getBody();
        System.out.println(prodBody);
        assertEquals(PROD_RESP, prodBody);
    }

}
