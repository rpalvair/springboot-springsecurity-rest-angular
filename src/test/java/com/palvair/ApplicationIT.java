package com.palvair;

import com.palvair.configurations.StatelessAuthenticationSecurityConfig;
import com.palvair.security.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public void testToken() {
        HttpHeaders httpHeaders = getToken();
        System.out.println("headers = " + httpHeaders);
    }

    @Test
    public void testUserCurrent() {
        HttpHeaders httpHeaders = getToken();
        HttpEntity<String> testRequest = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<User> testResponse = restTemplate.exchange("http://localhost:8080/api/users/current", HttpMethod.GET, testRequest,
                User.class);
        System.out.println("testResponse = " + testResponse);
        Assert.assertEquals(HttpStatus.OK, testResponse.getStatusCode());
    }

    @Test
    public void testListUsers() {
        HttpHeaders httpHeaders = getToken();
        HttpEntity<String> testRequest = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<List> testResponse = restTemplate.exchange("http://localhost:8080/admin/api/users", HttpMethod.GET, testRequest,
                List.class);
        System.out.println("testResponse = " + testResponse);
        Assert.assertEquals(HttpStatus.OK, testResponse.getStatusCode());
        Assert.assertNotNull(testResponse.getBody());
        Assert.assertTrue(testResponse.getBody().size() > 0);
    }

    private HttpHeaders getToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String password = "admin";
        String username = "admin";
        HttpEntity<String> login = new HttpEntity<>(
                "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}", httpHeaders);
        ResponseEntity<Void> results = restTemplate.postForEntity("http://localhost:8080/api/login", login, Void.class);
        Assert.assertEquals(HttpStatus.OK, results.getStatusCode());
        Assert.assertNotNull(results.getHeaders().getFirst("X-AUTH-TOKEN"));
        httpHeaders.add("X-AUTH-TOKEN", results.getHeaders().getFirst("X-AUTH-TOKEN"));
        return httpHeaders;
    }
}
