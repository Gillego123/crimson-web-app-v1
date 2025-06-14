package com.crimson.app.crimson.controller;

import com.crimson.app.crimson.model.User;
import com.crimson.app.crimson.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImp userServiceImp;

    //Register User
    @PostMapping("/register")
    public ResponseEntity<User> regUser(@RequestBody User user ){
        return ResponseEntity.ok(userServiceImp.registerUser(user));
    }

    //Update User
    @PutMapping("/update/{userId}")
    public  ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails){

        try {
            User updateUser = userServiceImp.udpateUser(userId, userDetails);
            return ResponseEntity.ok(updateUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //Delete Users
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){

        if(!userServiceImp.existsById(userId)){
            return ResponseEntity.status(404).body("User not found");
        }
        userServiceImp.deleteUserById(userId);
        return  ResponseEntity.ok("User deleted successfully");

    }

    //Display all Users
    @GetMapping("/listUser")
    public ResponseEntity<List<User>> listOfUser(){
        List<User> userList = userServiceImp.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
