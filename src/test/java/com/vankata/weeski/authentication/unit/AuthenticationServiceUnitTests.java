package com.vankata.weeski.authentication.unit;

import com.vankata.weeski.exception.EmailExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AuthenticationServiceUnitTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Before
//    public void seedRoles() {
//        Role role = new Role();
//        role.setAuthority(Authority.ROLE_USER);
//        this.roleRepository.save(role);
//    }

    @Test
    public void testRegister_withEmailAndPassword_passwordShouldBeEncoded() {
        // arrange
//        UserRepository userRepoMock = mock(UserRepository.class);
//
//        final String EMAIL = UUID.randomUUID().toString() + "@yahoo.com";
//        final String PASSWORD = "yonkov123";
//        final String ENCODED_PASSWORD = this.passwordEncoder.encode(PASSWORD);
//
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setEmail(EMAIL);
//        registerRequest.setPassword(PASSWORD);
//        registerRequest.setConfirmPassword(PASSWORD);
//
//        // act
//        this.authenticationService.register(registerRequest, null);
//        User user = this.userRepository.findByEmail(EMAIL).get();
//
//        // assert
//        assertTrue("Password is not encoded correctly!",
//                passwordEncoder.matches(PASSWORD, ENCODED_PASSWORD));
    }

    @Test(expected = EmailExistsException.class)
    public void testRegister_withExistingEmail_shouldThrowEmailExistsException() {
        // arrange
//        final String EMAIL = "yonkov@yahoo.com";
//        final String PASSWORD = "yonkov123";
//
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setEmail(EMAIL);
//        registerRequest.setPassword(PASSWORD);
//        registerRequest.setConfirmPassword(PASSWORD);
//
//        // act
//        this.authenticationService.register(registerRequest, null);
    }
}
