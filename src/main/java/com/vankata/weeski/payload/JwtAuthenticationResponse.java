package com.vankata.weeski.payload;

import com.vankata.weeski.security.UserPrincipal;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserPrincipal user;

    public JwtAuthenticationResponse(String accessToken, UserPrincipal user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
