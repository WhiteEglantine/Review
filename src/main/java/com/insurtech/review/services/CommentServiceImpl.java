package com.insurtech.review.services;

import com.insurtech.review.domain.Comment;
import com.insurtech.review.domain.Product;
import com.insurtech.review.domain.User;
import com.insurtech.review.repositories.CommentRepository;
import com.insurtech.review.repositories.ProductRepository;
import com.insurtech.review.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository,
                              ProductRepository productRepository,
                              CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public int getCommentCount(Long productId) {
        return commentRepository.findConfirmedCount(productId);
    }

    @Override
    @Transactional
    public Comment saveComment(Long productId, Long userId, String value) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Comment comment = null;
        if (user != null && product != null && product.isSaveComment()) {
            comment = new Comment();
            comment.setCreationTimestamp(System.currentTimeMillis());
            comment.setValue(value);
            comment.setProduct(product);
            comment.setUser(user);
            commentRepository.save(comment);
        }
        return comment;
    }

    @Override
    public Set<Comment> getTopComments(Long productId) {
        return commentRepository.findTop3ByProductIdOrderByCreationTimestampDesc(productId);
    }

    @Override
    @Transactional
    public Comment updateCommentStatus(Long id, boolean status) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        Comment comment = null;
        if (commentOptional.isPresent()) {
            comment = commentOptional.get();
            if (!comment.isConfirmed()) {
                comment.setConfirmed(status);
                commentRepository.save(comment);
            }
        }
        return comment;
    }
}
