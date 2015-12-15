package com.palvair;

import com.palvair.security.TokenHandler;
import com.palvair.security.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by PALVAIRR on 15/12/2015.
 */

public class TokenHandlerTest {

    private String KEY = "kdekdekdekdoe";

    private TokenHandler tokenHandler = new TokenHandler(KEY.getBytes());

    private User user = new User("admin", new Date(new Date().getTime() + 10000));

    @Test
    public void testCreateToken() {
        final String token = tokenHandler.createTokenForUser(user);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseUserFromToken() {
        final String token = tokenHandler.createTokenForUser(user);
        System.out.println("token = " + token);
        final User userFromToken = tokenHandler.parseUserFromToken(token);
        System.out.println("userFromToken = " + userFromToken);
        Assert.assertEquals(userFromToken, user);
    }
}
