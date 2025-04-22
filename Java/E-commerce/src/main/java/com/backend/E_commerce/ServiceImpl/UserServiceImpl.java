package com.backend.E_commerce.ServiceImpl;

import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.Repository.UserRepository;
import com.backend.E_commerce.Service.UserService;
import com.backend.E_commerce.config.JwtProvider;
import com.backend.E_commerce.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long id) throws UserException {
        Optional<User>  user =userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        throw  new UserException("User not found with id"+id);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
       String email = jwtProvider.getEmailFromToken(jwt);
       User user = userRepository.findUserByEmail(email);
       if(user==null)
           throw new UserException("User not found with email "+email);
       return user;
    }
}
