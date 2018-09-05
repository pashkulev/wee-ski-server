package com.vankata.weeski.controller;

import com.vankata.weeski.domain.social.comment.model.CommentBindingModel;
import com.vankata.weeski.service.CommentService;
import com.vankata.weeski.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody CommentBindingModel commentBindingModel,
                                 Principal principal) {
        ApiResponse response = this.commentService.create(commentBindingModel, principal);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);
    }
}
