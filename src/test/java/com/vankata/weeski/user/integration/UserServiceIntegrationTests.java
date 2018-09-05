package com.vankata.weeski.user.integration;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.role.model.Role;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.service.UserService;
import com.vankata.weeski.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testFindById_shouldFindUser() {
        // arrange
        final String USER_ID = "ed970aa0-c9d6-43ee-bd5e-eb37c5fb1428";

        // act
        User user = this.userService.findById(USER_ID);

        // assert
        assertNotNull("User is null!", user);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindById_shouldThrowResourceNotFoundException() {
        // arrange
        final String NON_EXISTING_USER_ID = "ed970aa0-c9d6-43ee-bd5e-eb37c5fb142";

        // act
        this.userService.findById(NON_EXISTING_USER_ID);
    }

    @Test
    public void testFindByEmail_shouldFindUser() {
        // arrange
        final String USER_EMAIL = "ivan@abv.bg";

        // act
        User user = this.userService.findByEmail(USER_EMAIL);

        // assert
        assertNotNull("User is null!", user);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindByEmail_shouldThrowResourceNotFoundException() {
        // arrange
        final String NON_EXISTING_EMAIL = "non-existing@email.com";

        // act
        this.userService.findById(NON_EXISTING_EMAIL);
    }

    @Test
    public void testSetUserAuthorities_withAdminAuthority_shouldSetAdminRole() {
        // arrange
        final String USER_ID = "a3db7b4f-a24d-446f-8db0-1e94682cf759";
        Authority[] authorities = new Authority[] {Authority.ROLE_USER, Authority.ROLE_ADMIN};

        // act
        this.userService.setUserAuthorities(USER_ID, authorities);
        User user = this.userService.findById(USER_ID);

        // assert
        assertTrue("User doesn't have ROLE_ADMIN", user.getRoles()
                .stream()
                .map(Role::toString)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN"));
    }
}
