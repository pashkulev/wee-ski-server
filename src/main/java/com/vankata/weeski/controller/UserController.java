package com.vankata.weeski.controller;

import com.vankata.weeski.domain.role.Authority;
import com.vankata.weeski.domain.user.service.UserService;
import com.vankata.weeski.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}/authorities")
    public ResponseEntity<ApiResponse> setAuthorities(@RequestBody Authority[] authorities,
                                                      @PathVariable String id) {
        ApiResponse response = this.userService.setUserAuthorities(id, authorities);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}