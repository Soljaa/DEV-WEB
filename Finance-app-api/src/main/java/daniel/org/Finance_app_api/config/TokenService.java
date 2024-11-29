package daniel.org.Finance_app_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import daniel.org.Finance_app_api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public static final int TOKEN_DURATION = 5 * 60 * 60 * 1000; // 5H

    public String generateToken(User user) throws IllegalArgumentException, JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("daniel.org")
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(Duration.ofSeconds(TOKEN_DURATION)))
                .sign(algorithm);
    }

    public String validateToken(String token) throws IllegalArgumentException, JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("daniel.org")
                .build()
                .verify(token)
                .getSubject();
    }
}
