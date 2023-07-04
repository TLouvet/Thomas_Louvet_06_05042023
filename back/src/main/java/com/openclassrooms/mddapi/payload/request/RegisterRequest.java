package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class RegisterRequest {

    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 8)
    private String password;

    @NotBlank
    private String username;
}
