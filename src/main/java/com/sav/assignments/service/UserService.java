package com.sav.assignments.service;

import com.sav.assignments.dto.UpdateUserRequest;
import com.sav.assignments.dto.UserDTO;
import com.sav.assignments.entity.AppUser;
import javassist.NotFoundException;

public interface UserService {
    UserDTO loadUserByUsername(String username) throws NotFoundException;
    UserDTO update(String username, UpdateUserRequest updateUserRequest) throws NotFoundException;
}
