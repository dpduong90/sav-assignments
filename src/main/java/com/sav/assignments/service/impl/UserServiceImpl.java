package com.sav.assignments.service.impl;

import com.sav.assignments.dto.UpdateUserRequest;
import com.sav.assignments.dto.UserDTO;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.repository.UserRepository;
import com.sav.assignments.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO loadUserByUsername(String username) throws NotFoundException {
        AppUser user = userRepository.findOneByUsername(username)
            .orElseThrow(() -> new NotFoundException(String.format("User not found with username %s", username)));
        return new UserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO update(String username, UpdateUserRequest updateUserRequest) throws NotFoundException {
        AppUser user = userRepository.findOneByUsername(username)
            .orElseThrow(() -> new NotFoundException(String.format("User not found with username %s", username)));

        try {
            user.setFirstName(updateUserRequest.getFirstName());
            user.setLastName(updateUserRequest.getLastName());

            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            logger.error(String.format("Update user for username %s failed", username), e);
            throw e;
        }

        return new UserDTO(user);
    }
}
