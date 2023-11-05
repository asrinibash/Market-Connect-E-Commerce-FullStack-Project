package com.cutm.marketconnect.Controller;

import com.cutm.marketconnect.Exception.UserException;
import com.cutm.marketconnect.Model.User;
import com.cutm.marketconnect.Repository.UserRepository;
import com.cutm.marketconnect.Request.LoginRequest;
import com.cutm.marketconnect.Response.AuthResponse;
import com.cutm.marketconnect.Service.CustomUserServiceImplementation;
import com.cutm.marketconnect.config.JwtProvider;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    private CustomUserServiceImplementation customUserServiceImplementation;

    public AuthController(UserRepository userRepository, CustomUserServiceImplementation customUserServiceImplementation, PasswordEncoder passwordEncoder,JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.customUserServiceImplementation = customUserServiceImplementation;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider=jwtProvider;
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {


        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String mobile=user.getMobile();
        String role=user.getRole();

        //checking the provided email is exist or not

        User isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
            throw new UserException("Email is Already used with Another Account");
        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setMobile(mobile);
        createdUser.setRole(role);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);


        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignUp Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token, "SignIn Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid Username");
            //BadCredentialsException is for username is invalid
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
