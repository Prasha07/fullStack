package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.UserRepository;
import com.backend.E_commerce.Service.AuthService;
import com.backend.E_commerce.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private DefaultSecurityOverrideLogIn defaultSecurityOverrideLogIn;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthServiceImpl(UserRepository userRepository,DefaultSecurityOverrideLogIn defaultSecurityOverrideLogIn,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.defaultSecurityOverrideLogIn=defaultSecurityOverrideLogIn;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public User checkIfEmailExists(String email) throws UserException {
        User user =  userRepository.findUserByEmail(email);
        if(user!=null)
        throw new UserException("User is Already registered with this email.");
        return user;
    }

    @Override
    public User saveNewUser(User user) throws UserException {
            User newUser = userRepository.save(user);
            if(newUser == null){
                throw new UserException("Some problem in saving a new user");
            }
            return newUser;
    }

    @Override
    public Authentication authenticateUser(String userName, String password) {
        UserDetails userDetails = defaultSecurityOverrideLogIn.loadUserByUsername(userName);
        if(userDetails==null)
            throw new BadCredentialsException("Invalid username");
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invaid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
