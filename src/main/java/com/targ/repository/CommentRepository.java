package com.targ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.targ.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
