package com.crimson.app.crimson.service;

import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.repository.UserRepository;
import com.crimson.app.crimson.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

//    @Autowired
//    private UserRepository userRepository;
//

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(User user) {

        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());

        String encodedPassword = passwordEncoder.encode(user.getPassWordHash());

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMiddleName(user.getMiddleName());
        newUser.setPassWordHash(encodedPassword);
        newUser.setRole(user.getRole());

        if(userEmail.isPresent()){
            throw new RuntimeException("Failed to save user, email already in use");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserId(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User udpateUser(Long userId, User userDetails) {
        boolean isExist = userRepository.existsById(userId);
        if(!isExist){
            throw new RuntimeException("User not found");
        }
        return userRepository.save(userDetails);
    }


}
