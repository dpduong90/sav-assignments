package com.sav.assignments.service.impl;

import com.sav.assignments.dto.UserDTO;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
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

    public AppUser save(UserDTO user) {
        Optional<AppUser> userOpt = userRepository.findOneByUsername(user.getUserName());

        if (!userOpt.isPresent()) {
            AppUser newUser = new AppUser();
            newUser.setUsername(user.getUserName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            return this.userRepository.save(newUser);
        }

        return null;
    }
}
