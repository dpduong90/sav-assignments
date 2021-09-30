package com.sav.assignments.controller;

import com.sav.assignments.dto.UserInfoResponse;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.security.CurrentUser;
import com.sav.assignments.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/me")
    public ResponseEntity<?> getInfo(@CurrentUser User user) throws NotFoundException {
        final AppUser userDetails = userService.loadUserByUsername(user.getUsername());

        return ResponseEntity.ok(new UserInfoResponse(userDetails));
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable(value = "username") String username) throws NotFoundException {
        final AppUser userDetails = userService.loadUserByUsername(username);

        return ResponseEntity.ok(new UserInfoResponse(userDetails));
    }
}
