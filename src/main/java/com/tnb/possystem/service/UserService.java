package com.tnb.possystem.service;

import com.tnb.possystem.exceptions.UserException;
import com.tnb.possystem.modal.User;

import java.util.List;

public interface UserService {

    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUsers();

}
