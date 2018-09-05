package com.vankata.weeski.service;

import com.vankata.weeski.domain.social.comment.model.Comment;
import com.vankata.weeski.domain.social.comment.model.CommentBindingModel;
import com.vankata.weeski.repository.CommentRepository;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.payload.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse create(CommentBindingModel commentBindingModel, Principal principal) {
        Comment comment = this.modelMapper.map(commentBindingModel, Comment.class);
        User author = this.userService.findByEmail(principal.getName());
        comment.setAuthor(author);
        this.commentRepository.save(comment);

        return new ApiResponse(true, "Comment created successfully!");
    }
}
