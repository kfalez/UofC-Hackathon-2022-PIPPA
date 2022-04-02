package com.project.pippa.controller;

import com.project.pippa.model.User;
import com.project.pippa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "https://pippa-application.herokuapp.com")
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
        try{
            List<User> userList = userService.listAllUsers();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<?> getUser(@PathVariable String username, @PathVariable String password){
        try{
            User existingUser = userService.findByUsername(username);
            String loggingInUserName = username;
            String loggingInPassword = password;
            if(existingUser.getUsername().equals(loggingInUserName)
                    && existingUser.getThePassword().equals(loggingInPassword)){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
