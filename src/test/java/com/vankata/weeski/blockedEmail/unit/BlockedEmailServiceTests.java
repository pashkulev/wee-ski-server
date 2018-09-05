package com.vankata.weeski.blockedEmail.unit;

import com.vankata.weeski.domain.blockedEmail.model.BlockedEmail;
import com.vankata.weeski.repository.BlockedEmailRepository;
import com.vankata.weeski.service.BlockedEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BlockedEmailServiceTests {

    @Mock
    private BlockedEmailRepository blockedEmailRepository;

    @InjectMocks
    private BlockedEmailService blockedEmailService;

    @Test
    public void testIsEmailBlocked_withBlockedEmail_shouldReturnTrue() {
        // arrange
        final String EMAIL = "pesho@abv.bg";
        BlockedEmail blockedEmail = new BlockedEmail(EMAIL);
        when(this.blockedEmailRepository.findByEmail(anyString())).thenReturn(Optional.of(blockedEmail));

        // act
        boolean result = this.blockedEmailService.isEmailBlocked(EMAIL);

        // assert
        assertTrue("Blocked email is ignored", result);
    }

    public void testIsEmailBlocked_withNonBlockedEmail_shouldReturnFalse() {

    }

}
