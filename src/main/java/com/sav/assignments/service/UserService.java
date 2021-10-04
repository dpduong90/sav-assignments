package com.sav.assignments.service;

import com.sav.assignments.dto.UpdateUserRequest;
import com.sav.assignments.dto.UserDTO;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.entity.VerificationToken;
import javassist.NotFoundException;

public interface UserService {
    UserDTO loadUserByUsername(String username) throws NotFoundException;

    UserDTO update(String username, UpdateUserRequest updateUserRequest) throws NotFoundException;

    void createVerificationToken(AppUser user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredUser(AppUser user);
}
