package com.palvair;

import com.palvair.configurations.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by widdy on 14/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class ApplicationIT {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testRest() {
        //ResponseEntity<String> response = restTemplate.postForEntity()

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080//api/login", HttpMethod.POST,null, String.class);
        HttpStatus status = response.getStatusCode();
        System.out.println("status = " + status);
        String restCall = response.getBody();

        System.out.println("body = " + response.getBody());
    }
}
