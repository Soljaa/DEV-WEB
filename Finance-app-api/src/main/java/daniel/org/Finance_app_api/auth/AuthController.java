package daniel.org.Finance_app_api.auth;

import com.auth0.jwt.exceptions.JWTCreationException;
import daniel.org.Finance_app_api.model.User;
import daniel.org.Finance_app_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import daniel.org.Finance_app_api.config.TokenService;


@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public TokenResponse login(@RequestBody AuthRecord authRecord) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authRecord.username(), authRecord.password()
        );

        try {
            Authentication authentication = authenticationManager.authenticate(usernamePassword);
            User user = (User) authentication.getPrincipal();
            var token = tokenService.generateToken(user);

            return new TokenResponse(token, user.id);
        }
        catch (IllegalArgumentException |
               JWTCreationException |
               AuthenticationException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @PostMapping("create")
    public User create(@RequestBody UserRecord userRecord) {
        if (userService.loadUserByUsername(userRecord.username()) == null) {
            User user = new User(
                    userRecord.username(),
                    passwordEncoder.encode(userRecord.password()),
                    userRecord.role()
            );

            userService.create(user);
            return user;
        }
        else {
            throw new RuntimeException("Usuário já cadastrado!");
        }
    }
}
