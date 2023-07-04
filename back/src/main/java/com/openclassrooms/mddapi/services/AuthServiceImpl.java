package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.interfaces.AuthService;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public JwtResponse login(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = this.userRepository.findByEmailOrUsername(username);

        if (!this.isUserValid(user, password)){
            throw new UnauthorizedException();
        }

        return this.jwtProvider.provideJwt(user);
    }

    public String register(RegisterRequest registerRequest){
        String email = registerRequest.getEmail();
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        if (this.userRepository.existsByEmailOrUsername(email, username) != 0){
          throw new BadRequestException();
        }

        if (!this.isValidRegistrationPassword(password)){
            throw new BadRequestException();
        }

        String hashedPassword = this.passwordEncoder.encode(password);

        User user = new User(
                email,
                username,
                hashedPassword
        );

        this.userRepository.save(user);

        return "User registered successfully";
    }

    public boolean isValidRegistrationPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+!=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private boolean isUserValid(User user, String rawPassword){
        return user != null && this.passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
