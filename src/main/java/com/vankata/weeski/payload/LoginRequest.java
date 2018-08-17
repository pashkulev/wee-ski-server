package com.vankata.weeski.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank
    @Pattern(regexp = "^([a-zA-Z\\d]+[.\\-_]?[a-zA-Z\\d]+)+@([a-z]+\\.)+[a-z]{2,4}$", message = "Invalid e-mail!")
    private String email;

    @NotBlank
    @Size(min = 4, message = "Password should contain at least 4 characters!")
    private String password;
}
