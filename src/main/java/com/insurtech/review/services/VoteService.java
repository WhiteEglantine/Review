package com.insurtech.review.services;

import com.insurtech.review.domain.Vote;

public interface VoteService {

    Vote getVote(Long id);

    double getVoteAverage(Long productId);

    Vote saveVote(Long productId, Long userId, int value);

    Vote updateVoteStatus(Long id, boolean status);
}
