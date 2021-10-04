package com.sav.assignments.service;

import com.sav.assignments.entity.AppUser;

public interface EmailService {
    void sendMailRegister(AppUser newUser);
}
