package com.backend.E_commerce.Service;

import com.backend.E_commerce.Entity.User;
import com.backend.E_commerce.exception.UserException;

public interface UserService {
    public User findUserById(Long id) throws UserException;
    public  User findUserProfileByJwt(String jwt) throws UserException;
}
