package com.insurtech.review.services;

import com.insurtech.review.domain.Comment;

import java.util.Set;

public interface CommentService {

    Comment getComment(Long id);

    int getCommentCount(Long productId);

    Comment saveComment(Long productId, Long userId, String value);

    Set<Comment> getTopComments(Long productId);

    Comment updateCommentStatus(Long id, boolean status);
}
