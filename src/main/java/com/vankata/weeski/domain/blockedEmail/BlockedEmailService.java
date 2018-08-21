package com.vankata.weeski.domain.blockedEmail;

public interface BlockedEmailService {

    boolean isEmailBlocked(String email);

    void addBlockedEmail(String email);
}
