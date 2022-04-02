package com.project.pippa.service;

import com.project.pippa.model.User;
import com.project.pippa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers(){return userRepository.findAll();}

    public void saveUser(User user){userRepository.save(user);}

    public Optional<User> getUser(Integer id){return userRepository.findById(id);}

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}