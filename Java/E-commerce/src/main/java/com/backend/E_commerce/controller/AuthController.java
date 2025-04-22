package com.backend.E_commerce.controller;

import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.request.LoginRequest;
import com.backend.E_commerce.ResponseForms.AuthResponse;
import com.backend.E_commerce.Service.AuthService;
import com.backend.E_commerce.exception.UserException;
import com.backend.E_commerce.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private JwtProvider jwtProvider;
//    alreadybean is present in appconfig.
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthService authService,JwtProvider jwtProvider,PasswordEncoder passwordEncoder){
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder=passwordEncoder;
    }
     @PostMapping(value = "/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws UserException {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
        User doEmailExists = authService.checkIfEmailExists(email);
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        User createdUser = null;
        createdUser = authService.saveNewUser(newUser);
//         	Authentication auth = new UsernamePasswordAuthenticationToken(...)	Create an authentication token with user details and authorities	Preparing a security badge for an employee
        Authentication authentication =new UsernamePasswordAuthenticationToken(createdUser.getEmail(),createdUser.getPassword());
//         SecurityContextHolder.getContext().setAuthentication(auth)	Set the authentication token in the security context	Employee presenting the badge to gain access
        SecurityContextHolder.getContext().setAuthentication(authentication);
// Now we will create a jwt token as the user is logged in now
        String token =jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token , "SignUp successful");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest){
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authenticate = authService.authenticateUser(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // Now we will create a jwt token as the user is logged in now
        String token =jwtProvider.generateToken(authenticate);
        AuthResponse authResponse = new AuthResponse(token , "SignIn successful");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }
}
