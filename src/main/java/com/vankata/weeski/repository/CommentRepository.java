package com.vankata.weeski.repository;

import com.vankata.weeski.domain.social.comment.model.Comment;
import com.vankata.weeski.domain.social.comment.model.CommentViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(excerptProjection = CommentViewModel.class)
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByEntityIdOrderByCreatedAtDesc(@Param("entityId") String entityId);
}
