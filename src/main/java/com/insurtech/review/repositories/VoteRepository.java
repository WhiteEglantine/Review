package com.insurtech.review.repositories;

import com.insurtech.review.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote,Long> {
    @Query(value = "SELECT AVG(value) FROM VOTE v WHERE v.confirmed = true AND v.product_id = ?1",
            nativeQuery = true)
    int findConfirmedAverage(Long productId);
}
