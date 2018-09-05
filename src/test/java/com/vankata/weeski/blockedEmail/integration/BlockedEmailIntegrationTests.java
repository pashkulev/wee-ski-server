package com.vankata.weeski.blockedEmail.integration;

import com.vankata.weeski.domain.blockedEmail.model.BlockedEmail;
import com.vankata.weeski.repository.BlockedEmailRepository;
import com.vankata.weeski.service.BlockedEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockedEmailIntegrationTests {

    @Autowired
    private BlockedEmailService blockedEmailService;

    @Autowired
    private BlockedEmailRepository blockedEmailRepository;

    @Test
    public void testIsEmailBlocked_withBlockedEmail_shouldReturnTrue() {
        // arrange
        final String BLOCKED_EMAIL = "vivi@yahoo.com";

        // act
        boolean result = this.blockedEmailService.isEmailBlocked(BLOCKED_EMAIL);

        // assert
        assertTrue("isEmailBlocked() returns incorrect result!", result);
    }

    @Test
    public void testIsEmailBlocked_withBlockedEmail_shouldReturnFalse() {
        // arrange
        final String NON_BLOCKED_EMAIL = "random_email@yahoo.com";

        // act
        boolean result = this.blockedEmailService.isEmailBlocked(NON_BLOCKED_EMAIL);

        // assert
        assertFalse("isEmailBlocked() returns incorrect result!", result);
    }

    @Test
    public void testAddBlockedEmail_shouldAddBlockedEmail() {
        // arrange
        final String BLOCKED_EMAIL = UUID.randomUUID().toString() + "@yahoo.com";

        // act
        this.blockedEmailService.addBlockedEmail(BLOCKED_EMAIL);
        BlockedEmail blockedEmail = this.blockedEmailRepository.findByEmail(BLOCKED_EMAIL).get();

        // assert
        assertNotNull("Blocked email is not added!", blockedEmail);
    }
}
