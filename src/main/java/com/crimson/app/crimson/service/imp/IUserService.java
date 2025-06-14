package com.crimson.app.crimson.service.imp;

import com.crimson.app.crimson.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User registerUser(User user);
    Optional<User> getUserId(Long userId);
    List<User> getAllUsers();
    void deleteUserById(Long userId);
    Boolean existsById(Long userId);
    User udpateUser(Long userId, User userDetails);

}
