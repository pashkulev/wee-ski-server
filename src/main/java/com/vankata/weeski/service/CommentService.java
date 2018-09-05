package com.vankata.weeski.service;

import com.vankata.weeski.domain.social.comment.model.CommentBindingModel;
import com.vankata.weeski.payload.ApiResponse;

import java.security.Principal;

public interface CommentService {

    ApiResponse create(CommentBindingModel commentBindingModel, Principal principal);


}
