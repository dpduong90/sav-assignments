package com.sav.assignments.controller;

import com.sav.assignments.dto.LoginRequest;
import com.sav.assignments.dto.LoginResponse;
import com.sav.assignments.dto.RegisterRequest;
import com.sav.assignments.dto.UserDTO;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.entity.VerificationToken;
import com.sav.assignments.security.JwtTokenUtil;
import com.sav.assignments.service.UserService;
import com.sav.assignments.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

        authenticate(loginRequest.getUserName(), loginRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());

        if (!userDetails.isEnabled()) {
            return new ResponseEntity<>("Please registration confirm before login", HttpStatus.UNAUTHORIZED);
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        final UserDTO appUser = userDetailsService.save(registerRequest);
        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return new ResponseEntity<>("Verification token is not found", HttpStatus.NOT_FOUND);
        }

        AppUser user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>("Verification token has expired", HttpStatus.UNAUTHORIZED);
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return ResponseEntity.ok("User is actived!");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
