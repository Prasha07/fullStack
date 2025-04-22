package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// This is to overcome by default spring configuration which by default has user as userName and password comes when we run the application
@Service
public class DefaultSecurityOverrideLogIn implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public DefaultSecurityOverrideLogIn(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("User is not present with given email.Please enter valid email id ");
        List<GrantedAuthority> authorities = new ArrayList<>();
//        this will be used by spring    to authenticate the individuals now
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
