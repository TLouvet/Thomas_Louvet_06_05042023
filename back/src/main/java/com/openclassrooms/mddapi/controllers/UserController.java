package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/me")
    public ResponseEntity<?> findUser() {
        User user = this.userService.getUserProfile();
        return ResponseEntity.ok(this.modelMapper.map(user, UserDto.class));
    }

    @PutMapping("/me")
    public ResponseEntity<?> update(@Valid @RequestBody UserDto userDto) {
        User user = this.userService.updateUserProfile(this.modelMapper.map(userDto, User.class));
        return ResponseEntity.ok(this.modelMapper.map(user, UserDto.class));
    }
}
