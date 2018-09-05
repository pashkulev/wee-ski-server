package com.vankata.weeski.controller;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.service.UserService;
import com.vankata.weeski.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{userId}/authorities")
    @PreAuthorize("hasAuthority('ROLE_ROOT_ADMIN')")
    public ResponseEntity<ApiResponse> setAuthorities(@RequestBody Authority[] authorities,
                                                      @PathVariable String userId) {
        ApiResponse response = this.userService.setUserAuthorities(userId, authorities);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}