package com.sav.assignments.service.impl;

import com.sav.assignments.entity.AppUser;
import com.sav.assignments.repository.UserRepository;
import com.sav.assignments.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppUser loadUserByUsername(String username) throws NotFoundException {
        AppUser user = userRepository.findOneByUsername(username)
            .orElseThrow(() -> new NotFoundException(String.format("User not found with username %s", username)));
        return user;
    }
}
