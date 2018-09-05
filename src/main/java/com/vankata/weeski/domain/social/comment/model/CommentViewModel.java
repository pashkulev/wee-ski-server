package com.vankata.weeski.domain.social.comment.model;

import com.vankata.weeski.domain.user.model.UserViewModel;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;

@Projection(types = Comment.class, name = "commentViewModel")
public interface CommentViewModel {

    String getId();

    String getContent();

    Instant getCreatedAt();

    UserViewModel getAuthor();
}
