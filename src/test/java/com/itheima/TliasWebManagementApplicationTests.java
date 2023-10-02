package com.itheima;


import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public void a() {
        Map<String, Object> map = new HashMap<>();
        map.put("yepin", "1234");
        System.out.println(JwtUtils.generateJwt(map));
    }

    @Test
    public void b() {
        Claims claims = Jwts.parser().setSigningKey("yepin2022").
                parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJ5ZXBpbiI6IjEyMzQiLCJleHAiOjE2OTQ4MjY0MDh9.O_l5pWEWKFIO6zA6_0xTJl693iF0ZN5b7hAGiYabvJA")
                .getBody();

        System.out.println(claims);

    }

    @Test
    public void c(){
    }


}
