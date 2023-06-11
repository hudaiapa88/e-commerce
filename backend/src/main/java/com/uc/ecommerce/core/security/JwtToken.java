package com.uc.ecommerce.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtToken {
    public static final String SIGNING_KEY = "hjdsfhgds876ds76fdsftsdfdsfdsf67ds6fds5f65f6ds5fds5fds55fds65fd5s6f5dsfds78f6s";
    public static final String ISSUER = "http://ecommerce.com";

    private final String token;
    private final Claims claims;

    private JwtToken(String token) {
        this.token = token;
        this.claims = Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static JwtToken parse(String token) {
        return new JwtToken(token);
    }

    public Claims getClaims() {
        return claims;
    }

    public boolean isNoneExpired() {
        return claims.getExpiration().after(new Date());
    }

    public static class Builder {

        private final Claims claims;

        public Builder() {
            claims = Jwts.claims();
            claims.setIssuedAt(new Date());
        }

        public Builder put(String key, Object obj) {
            claims.put(key, obj);
            return this;
        }

        public Builder subject(String subject) {
            claims.setSubject(subject);
            return this;
        }

        public Builder expiration(Date date) {
            claims.setExpiration(date);
            return this;
        }

        public String build() {

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuer(ISSUER)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS256,getSignInKey() )
                    .compact();
        }
        private Key getSignInKey() {
            byte[] keyBytes = Decoders.BASE64.decode(SIGNING_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }
    }

}