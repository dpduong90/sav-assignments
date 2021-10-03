package com.sav.assignments.controller;

import com.sav.assignments.dto.UpdateUserRequest;
import com.sav.assignments.dto.UserDTO;
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
    public ResponseEntity<UserDTO> getInfo(@CurrentUser User user) throws NotFoundException {
        final UserDTO userDTO = userService.loadUserByUsername(user.getUsername());

        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(value = "/me")
    public ResponseEntity<UserDTO> updateInfo(@CurrentUser User user,
                                                       @RequestBody UpdateUserRequest updateUserRequest) throws NotFoundException {
        final UserDTO userDTO = userService.update(user.getUsername(), updateUserRequest);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable(value = "username") String username) throws NotFoundException {
        final UserDTO userDTO = userService.loadUserByUsername(username);

        return ResponseEntity.ok(userDTO);
    }
}
