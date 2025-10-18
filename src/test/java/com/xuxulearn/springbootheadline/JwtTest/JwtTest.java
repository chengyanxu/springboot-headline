package com.xuxulearn.springbootheadline.JwtTest;

import com.xuxulearn.springbootheadline.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = com.xuxulearn.springbootheadline.utils.JwtHelper.class)
public class JwtTest {
    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test(){

        String token = jwtHelper.createToken(1);
        System.out.println("token = " + token);


        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }

}
