package com.vankata.weeski.service;

public interface BlockedEmailService {

    boolean isEmailBlocked(String email);

    void addBlockedEmail(String email);
}
