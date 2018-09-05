package com.vankata.weeski.authentication.integration;

import com.vankata.weeski.controller.AuthController;
import com.vankata.weeski.payload.JwtAuthenticationResponse;
import com.vankata.weeski.payload.LoginRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTests {

    @Autowired
    private AuthController authController;

    @Test
    public void  testLogin_withEmailAndPassword_shouldReturnStatusOK() {
        // arrange
        final String EMAIL = "ivan.pashkulev@gmail.com";
        final String PASSWORD = "1234";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(EMAIL);
        loginRequest.setPassword(PASSWORD);

        // act
        ResponseEntity<JwtAuthenticationResponse> response = this.authController.authenticateUser(loginRequest);

        // assert
        assertEquals("Incorrect response status!", 200, response.getStatusCodeValue());
    }

    @Test(expected = BadCredentialsException.class)
    public void  testLogin_withEmailAndPassword_shouldThrowBadCredentialsException() {
        // arrange
        final String EMAIL = "nonExisting@gmail.com";
        final String PASSWORD = "1234";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(EMAIL);
        loginRequest.setPassword(PASSWORD);

        // act
        this.authController.authenticateUser(loginRequest);
    }
}
