package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.interfaces.UserService;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.UserDetailsService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class UserServiceImpl implements UserService {

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

        try {
            this.userRepository.save(user);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return user;
    }
}
