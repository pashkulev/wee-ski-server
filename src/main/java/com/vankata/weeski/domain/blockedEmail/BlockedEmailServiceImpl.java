package com.vankata.weeski.domain.blockedEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlockedEmailServiceImpl implements BlockedEmailService {

    private final BlockedEmailRepository blockedEmailRepository;

    @Autowired
    public BlockedEmailServiceImpl(BlockedEmailRepository blockedEmailRepository) {
        this.blockedEmailRepository = blockedEmailRepository;
    }

    @Override
    public boolean isEmailBlocked(String email) {
        Optional<BlockedEmail> optionalBlockedEmail = this.blockedEmailRepository.findByEmail(email);
        return optionalBlockedEmail.isPresent();
    }

    @Override
    public void addBlockedEmail(String email) {
        BlockedEmail blockedEmail = new BlockedEmail(email);
        this.blockedEmailRepository.save(blockedEmail);
    }
}
