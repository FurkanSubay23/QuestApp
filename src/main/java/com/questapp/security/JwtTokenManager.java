package com.questapp.security;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenManager {
    /*
    @Value("${quest.app.secret}")
    private String APP_SECRET;
     */

    @Value("${quest.app.expires.in}")
    private long EXPIRES_IN;

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public String generateTokenManager(Authentication auth) {
        JwtUserDetail jwtUserDetail = (JwtUserDetail) auth.getPrincipal();  //getPrincipal() metodu, bir Authentication nesnesinin doğrulanmış kullanıcısını temsil eden nesneyi döndürür.
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(jwtUserDetail.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key).compact();
    }

    public boolean validateToken(String token) {
        return getUserIdFromJwt(token) != null && isExpired(token);
    }

    public Long getUserIdFromJwt(String token) {                        // Subjectı buradan çekebilmek için claims methodumuzu kullanıyoruz.
        return Long.parseLong(getClaimsAttribute(token).getSubject());
    }

    public boolean isExpired(String token) {
        Date expire = getClaimsAttribute(token).getExpiration();        // Claims ile expiration çek ve sorgula
        return expire.after(new Date());
    }

    private Claims getClaimsAttribute(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
