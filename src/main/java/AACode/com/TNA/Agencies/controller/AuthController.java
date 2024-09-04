package AACode.com.TNA.Agencies.controller;

import AACode.com.TNA.Agencies.config.JwtProvider;
import AACode.com.TNA.Agencies.model.Enums.USER_ROLE;
import AACode.com.TNA.Agencies.model.User;
import AACode.com.TNA.Agencies.repo.UserRepository;
import AACode.com.TNA.Agencies.request.LoginRequest;
import AACode.com.TNA.Agencies.response.AuthResponse;
import AACode.com.TNA.Agencies.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, CustomerUserDetailsService customerUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.customerUserDetailsService = customerUserDetailsService;
    }


    @PostMapping(path = "/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if (isEmailExist!=null){
            throw new Exception("Email is already used to another account!!!");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration Successful...");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = "/signing")
    public ResponseEntity<AuthResponse> Signing(@RequestBody LoginRequest loginRequest){

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successful...");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails==null){
            throw new BadCredentialsException("Invalid UserName!!!");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password!!!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
