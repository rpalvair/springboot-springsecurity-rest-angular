package com.palvair;

import com.palvair.configurations.StatelessAuthenticationSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by widdy on 14/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(StatelessAuthenticationSecurityConfig.class)
@WebIntegrationTest
public class ApplicationIT {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    @Timed(millis = 5000)
    public void testRest() {

        String password = "admin";
        String username = "admin";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> login = new HttpEntity<>(
                "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}", httpHeaders);
        ResponseEntity<Void> results = restTemplate.postForEntity("http://localhost:8080/api/login", login, Void.class);

        System.out.println("results = " + results);

        httpHeaders.add("X-AUTH-TOKEN", results.getHeaders().getFirst("X-AUTH-TOKEN"));

    }
}
