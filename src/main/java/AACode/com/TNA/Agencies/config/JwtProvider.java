package AACode.com.TNA.Agencies.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication){
        Collection<? extends GrantedAuthority>authorities = authentication.getAuthorities();
        String roles = populateAuthoroties(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration((new Date(new Date().getTime()+86400000)))
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return jwt;
    }

    public String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;

    }

    private String populateAuthoroties(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();

        for (GrantedAuthority authority:authorities){
            auth.add(authority.getAuthority());
        }
        return String.join(",", auth);
    }
}
