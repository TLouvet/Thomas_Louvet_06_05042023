package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    User user;

    @BeforeEach
    public void setup(){
        user = new User();
    }

    @Test
    public void itShouldValidatePassword() {
        String password = "Valid$P4ssw0rD";
        assertThat(authService.isValidRegistrationPassword(password)).isEqualTo(true);
    }

    @Test
    public void itShouldFailToValidatePasswords() {
        String password1 = "InvalidPasswordBecauseNoNumber";
        String password2 = "2Short#";
        String password3 = "123456789";
        String password4 = "abcdefghijklmnop";
        String password5 = "ABCDEFGHIJKLMNOP";
        String password6 = "123456laSo6";

        assertThat(authService.isValidRegistrationPassword(password1)).isEqualTo(false);
        assertThat(authService.isValidRegistrationPassword(password2)).isEqualTo(false);
        assertThat(authService.isValidRegistrationPassword(password3)).isEqualTo(false);
        assertThat(authService.isValidRegistrationPassword(password4)).isEqualTo(false);
        assertThat(authService.isValidRegistrationPassword(password5)).isEqualTo(false);
        assertThat(authService.isValidRegistrationPassword(password6)).isEqualTo(false);
    }
}
