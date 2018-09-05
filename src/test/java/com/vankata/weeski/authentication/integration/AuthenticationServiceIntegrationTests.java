package com.vankata.weeski.authentication.integration;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.role.model.Role;
import com.vankata.weeski.repository.RoleRepository;
import com.vankata.weeski.exception.EmailExistsException;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.repository.UserRepository;
import com.vankata.weeski.payload.ApiResponse;
import com.vankata.weeski.payload.JwtAuthenticationResponse;
import com.vankata.weeski.payload.LoginRequest;
import com.vankata.weeski.payload.RegisterRequest;
import com.vankata.weeski.service.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegister_withEmailAndPassword_shouldReturnSuccessMessage() {
        // arrange
        final String EXPECTED_MESSAGE = "Registration successful!";
        final String EMAIL = UUID.randomUUID().toString() + "@yahoo.com";
        final String PASSWORD = "1234";

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);

        // act
        ApiResponse response = this.authenticationService.register(registerRequest, null);

        // assert
        assertEquals("Not returning success message!", EXPECTED_MESSAGE, response.getMessage());
    }

    @Test
    public void testRegister_withEmailAndPassword_passwordShouldBeEncoded() {
        // arrange
        final String EMAIL = UUID.randomUUID().toString() + "@yahoo.com";
        final String PASSWORD = "1234";

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);

        // act
        this.authenticationService.register(registerRequest, null);
        User user = this.userRepository.findByEmail(EMAIL).get();

        // assert
        assertTrue("Password is not encoded correctly!",
                this.passwordEncoder.matches(registerRequest.getPassword(), user.getPassword()));
    }

    @Test(expected = EmailExistsException.class)
    public void testRegister_withExistingEmail_shouldThrowEmailExistsException() {
        // arrange
        final String EMAIL = "ivan.pashkulev@gmail.com";
        final String PASSWORD = "yonkov123";

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);

        // act
        this.authenticationService.register(registerRequest, null);
    }

    @Test
    public void testRegister_withEmailAndPassword_userRoleShouldBeSet() {
        // arrange
        final String EMAIL = UUID.randomUUID().toString() + "@yahoo.com";
        final String PASSWORD = "1234";

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);

        Role roleUser = this.roleRepository.findByAuthority(Authority.ROLE_USER).get();

        // act
        this.authenticationService.register(registerRequest, null);
        User user = this.userRepository.findByEmail(EMAIL).get();

        // assert
        assertTrue("ROLE_USER is not added!", user.getRoles()
                .stream()
                .map(Role::toString)
                .collect(Collectors.toList())
                .contains(roleUser.toString()));
    }

    @Test
    public void testLogin_withEmailAndPassword_shouldReturnJWT() {
        // arrange
        final String EMAIL = "ivan.pashkulev@gmail.com";
        final String PASSWORD = "1234";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(EMAIL);
        loginRequest.setPassword(PASSWORD);

        // act
        JwtAuthenticationResponse response = this.authenticationService.login(loginRequest);

        // assert
        assertNotNull("Access token was not generated!", response.getAccessToken());
    }

    @Test
    public void testLogin_withEmailAndPassword_shouldReturnUserPrincipalWithMatchingEmail() {
        // arrange
        final String EMAIL = "ivan.pashkulev@gmail.com";
        final String PASSWORD = "1234";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(EMAIL);
        loginRequest.setPassword(PASSWORD);

        // act
        JwtAuthenticationResponse response = this.authenticationService.login(loginRequest);

        // assert
        assertEquals("UserPrincipal doesn't match email!", EMAIL, response.getUser().getEmail());
    }

    @Test
    public void testLogin_withEmailAndPassword_shouldAuthenticateUser() {
        // arrange
        final String EMAIL = "ivan.pashkulev@gmail.com";
        final String PASSWORD = "1234";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(EMAIL);
        loginRequest.setPassword(PASSWORD);

        // act
        this.authenticationService.login(loginRequest);

        // assert
        assertTrue("User is not authenticated after login!",
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    }
}
