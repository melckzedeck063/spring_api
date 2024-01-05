package com.example.srms.jwt;

import com.example.srms.userService.UserDetailImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JWTUtils {

    private final String key =  "zedeck_srms_api";
    Logger logger  = LoggerFactory.getLogger(JWTUtils.class);

    public String generateJwtToken(Authentication authentication){
        UserDetailImpl userPrincipal = (UserDetailImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", new ArrayList<>())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateJwtRefreshToken(Authentication authentication){
        UserDetailImpl userPrincipal =  (UserDetailImpl)   authentication.getPrincipal();

        return   Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", new ArrayList<>())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUsernameFromJwtToken(String token) {
        String username =  Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().getSubject();
        logger.debug(username);
        return username;
    }

    public boolean validateJwtToken (String authToken) {
        try {
            Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException e){
            logger.info("Invalid JWT Signature: " + e.getMessage());
        }
        catch (MalformedJwtException e){
            logger.info("Invalid JWT  Token: " + e.getMessage());
        }
        catch (ExpiredJwtException e){
            logger.info("Expired  JWT Token: " + e.getMessage());
        }
        catch (UnsupportedJwtException  e){
            logger.info("JWT token is unsupported");
        }
        catch (IllegalArgumentException e){
            logger.info("JWT claims string is empty: {}" + e.getMessage());
        }
 return  false;
    }


}
