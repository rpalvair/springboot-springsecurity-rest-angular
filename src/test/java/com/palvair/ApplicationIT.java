package com.palvair;

import com.palvair.configurations.Application;
import com.palvair.configurations.StatelessAuthenticationSecurityConfig;
import com.palvair.security.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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
        //ResponseEntity<String> response = restTemplate.postForEntity()

        User user = new User();
        user.setPassword("password");
        user.setUsername("username");

// Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        //requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<User> requestEntity = new HttpEntity<User>(user, requestHeaders);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/login", HttpMethod.POST,requestEntity, String.class);
        HttpStatus status = response.getStatusCode();
        System.out.println("status = " + status);
        System.out.println("body = " + response.getBody());
        System.out.println("headers = "+response.getHeaders());
    }
}
