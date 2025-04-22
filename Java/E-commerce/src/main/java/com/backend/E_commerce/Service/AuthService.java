package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.exception.UserException;
import org.springframework.security.core.Authentication;

public interface AuthService {
    public User checkIfEmailExists(String email) throws UserException;
    public User saveNewUser(User user) throws UserException;
    public Authentication authenticateUser(String userName, String password);
}
