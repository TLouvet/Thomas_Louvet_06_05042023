package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.UserDetailsService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    public User getUserProfile() {
        return this.userDetailsService.getUser();
    }

    public User updateUserProfile(User user)  {
        User authenticatedUser = this.userDetailsService.getUser();
        user.setId(authenticatedUser.getId());
        user.setPassword(authenticatedUser.getPassword());

        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setEmail(user.getEmail());

        try {
            this.userRepository.save(authenticatedUser);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }
        return user;
    }

    private boolean hasSentSameValues(User user, User authenticatedUser){
        return user.getEmail().equals(authenticatedUser.getEmail()) && user.getUsername().equals(authenticatedUser.getUsername());
    }
}
