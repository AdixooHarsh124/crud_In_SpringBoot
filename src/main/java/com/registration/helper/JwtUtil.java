package com.registration.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//**
//* methods : for generating token
//*validate
//*isExp.


@Component
public class JwtUtil {

    private static final long JWT_TOKEN_VALIDITY=5*60*60*60;
    private static final String SECRET_KEY = "secret";

    /**
     * get a userName from Token
     */
    public String getUsernameFromToken(String token)
    {
        return getClainFromToken(token, Claims::getSubject);
    }

    /**
     * get ExpirationDate from Token
     */
    public Date getExpirationDateFromToken(String token)
    {
        return getClainFromToken(token,Claims::getExpiration);
    }

    public <T> T getClainFromToken(String token,Function <Claims, T> claimsResolver)
    {
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    /**
     * check for is token expired or not
     */
    private Boolean isTokenExpired(String token)
    {
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * generate token
     */
    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    /**
     * validate a Token
     */
    private boolean validateToken(String token,UserDetails userDetails)
    {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
