package com.example.srms.jwt;

import com.example.srms.userService.UserDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class JWTUtils {

    private final String key =  "mfumowangumfumowangumfumowangumfumowangumfumowangumfumowangumfumowangumfumowangu";

    Logger logger  = LoggerFactory.getLogger(JWTUtils.class);

    public String generateJwtToken(Authentication authentication){
        UserDetailImpl userPrincipal = (UserDetailImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(setPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }




}
