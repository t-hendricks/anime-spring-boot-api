package com.favAnime.demo.service;

import com.favAnime.demo.exception.InformationExistException;
import com.favAnime.demo.model.User;
import com.favAnime.demo.model.request.LoginRequest;
import com.favAnime.demo.model.response.LoginResponse;
import com.favAnime.demo.repository.UserRepository;
import com.favAnime.demo.security.JWTUtils;
import com.favAnime.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    // The passwordEncoder waits until Spring app is loaded with a 'bean'(Java Object)
    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }

    /**
     * Adds user to the repository.
     *
     * @param userObject {Object}
     * @return User {Object}
     * @throws InformationExistException
     */
    public User createUser(User userObject) {
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exist ");
        }
    }

    /**
     * Searches for an existing user in the repository.
     *
     * @param email {String}
     * @return User {Object}
     */
    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();

            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Error : user email or password is incorrect"));
        }
    }
}

