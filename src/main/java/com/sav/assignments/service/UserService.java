package com.sav.assignments.service;

import com.sav.assignments.entity.AppUser;
import javassist.NotFoundException;

public interface UserService {
    AppUser loadUserByUsername(String Username) throws NotFoundException;
}
