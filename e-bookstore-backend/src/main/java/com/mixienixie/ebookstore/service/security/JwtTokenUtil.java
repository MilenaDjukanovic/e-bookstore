package com.mixienixie.ebookstore.service.security;

import com.mixienixie.ebookstore.repo.authority.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for managing JWTs
 *
 * @author ndjordjieski
 */
@Component
public class JwtTokenUtil{

    /** Secret */
    @Value("${ebookstore.security.jwt.secret}") @Setter
    private String jwtSecret;
    /** Issuer */
    @Value("${ebookstore.security.jwt.issuer}") @Setter
    private String jwtIssuer;

    /**
     * Generates the JWT token for the user
     *
     * @param user User to generate token for
     * @return Generated token
     */
    public String generateAccessToken(UserEntity user){
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Gets the user id from the token
     *
     * @param token JWT token
     * @return User id
     */
    public String getUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    /**
     * Gets the username from the token
     *
     * @param token JWT token
     * @return Username
     */
    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    /**
     * Gets the expiration date of the token
     *
     * @param token JWT token
     * @return Expiration date
     */
    public Date getExpirationDate(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    /**
     * Validates the token
     * @param token JWT token
     * @return true if valid, otherwise false
     */
    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // #TODO logger e1
        } catch (MalformedJwtException ex) {
            // #TODO logger e2
        } catch (ExpiredJwtException ex) {
            // #TODO logger e3
        } catch (UnsupportedJwtException ex) {
            // #TODO logger e4
        } catch (IllegalArgumentException ex) {
            // #TODO logger e5
        }
        return false;
    }
}
