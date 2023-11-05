package com.cutm.marketconnect.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());

    //Method to generate token
    public String generateToken(Authentication auth){
        String jwt= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+846000000)) //this time is equal to 24hr
                .claim("email",auth.getName())
                .signWith(key).compact();

                return jwt;
    }


    //Method to claim email from token
    public String getEmailFromToken(String jwt){
        jwt=jwt.substring(7); //token we get with barrier keyword so we have to extract it

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }


}
