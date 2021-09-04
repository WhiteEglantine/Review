package com.insurtech.review.repositories;

import com.insurtech.review.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    @Query(value = "SELECT COUNT(*) FROM COMMENT c WHERE c.confirmed = true AND c.product_id = ?1",
            nativeQuery = true)
    int findConfirmedCount(Long productId);

    Set<Comment> findTop3ByProductIdOrderByCreationTimestampDesc(Long productId);
}
