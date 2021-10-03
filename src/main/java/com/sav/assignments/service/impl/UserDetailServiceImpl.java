package com.sav.assignments.service.impl;

import com.sav.assignments.dto.RegisterRequest;
import com.sav.assignments.dto.UserDTO;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOpt = userRepository.findOneByUsername(username);

        if (userOpt.isPresent()) {
            return new User(userOpt.get().getUsername(),
                userOpt.get().getPassword(), Collections.emptyList());
        }
        return null;
    }

    @Transactional
    public UserDTO save(RegisterRequest registerRequest) throws Exception {
        Optional<AppUser> userOpt = userRepository.findOneByUsername(registerRequest.getUserName());


        if (userOpt.isPresent()) {
            throw new Exception(String.format("Username %s is existed!", registerRequest.getUserName()));
        }

        UserDTO response;
        try {
            AppUser newUser = new AppUser();
            newUser.setUsername(registerRequest.getUserName());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setPassword(bcryptEncoder.encode(registerRequest.getPassword()));
            newUser.setFirstName(registerRequest.getFirstName());
            newUser.setLastName(registerRequest.getLastName());
            AppUser saveUser = userRepository.saveAndFlush(newUser);

            response = new UserDTO();
            response.setUserName(saveUser.getUsername());
            response.setEmail(saveUser.getEmail());
            response.setFirstName(saveUser.getFirstName());
            response.setLastName(saveUser.getLastName());

        } catch (Exception e) {
            logger.error("Add new feed failed", e);
            throw e;
        }



        return response;
    }
}
