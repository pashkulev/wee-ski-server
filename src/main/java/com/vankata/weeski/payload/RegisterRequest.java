package com.vankata.weeski.payload;

import com.vankata.weeski.domain.user.validation.PasswordsMatch;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@PasswordsMatch
public class RegisterRequest {

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Invalid First Name! Should start with capital letter followed by 1 or more lowercase english letters!")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]+(\\s?[A-Z][a-z]+)?$", message = "Invalid First Name! Should start with capital letter followed by 1 or more lowercase english letters!")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^([a-zA-Z\\d]+[.\\-_]?[a-zA-Z\\d]+)+@([a-z]+\\.)+[a-z]{2,4}$", message = "Invalid e-mail!")
    private String email;

    private String country;

    @NotBlank
    @Size(min = 4, message = "Password should contain at least 4 characters!")
    private String password;

    @NotBlank
    @Size(min = 4, message = "Confirm Password should contain at least 4 characters!")
    private String confirmPassword;

    private String profilePicture;
}
