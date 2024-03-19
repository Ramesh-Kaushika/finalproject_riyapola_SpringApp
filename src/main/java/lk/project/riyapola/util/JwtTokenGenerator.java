package lk.project.riyapola.util;

import lk.project.riyapola.entity.Admin;
import lk.project.riyapola.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenGenerator {

    @Value("${acpt.app.jwtSecret}")
    private String jwtSecret;
    @Value("${acpt.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Admin admin) {
        return Jwts.builder()
                .setId(String.valueOf(admin.getId()))
                .setSubject((admin.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateJwtTokenCustomer(Customer customer) {
        return Jwts.builder()
                .setId(String.valueOf(customer.getId()))
                .setSubject((customer.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Error when generating token...");
        }

        return false;
    }
}
