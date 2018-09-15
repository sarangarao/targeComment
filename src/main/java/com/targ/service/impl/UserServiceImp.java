package com.targ.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;

import com.targ.model.User;
import com.targ.repository.RoleRepository;
import com.targ.repository.UserRepository;
import com.targ.service.UserService;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
   
    private static final String USER_ROLE = "ROLE_USER";

     @Autowired
   public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
         this.userRepository = userRepository;
         this.roleRepository = roleRepository;
         
     }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // Encode plaintext password
        user.setPassword(user.getPassword());
        user.setActive(1);
        // Set Role to ROLE_USER
        user.setRoles(Collections.singletonList(roleRepository.findByRole(USER_ROLE)));
        return userRepository.saveAndFlush(user);
    }
}
