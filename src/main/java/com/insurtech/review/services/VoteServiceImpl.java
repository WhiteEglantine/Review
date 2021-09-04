package com.insurtech.review.services;

import com.insurtech.review.domain.Product;
import com.insurtech.review.domain.User;
import com.insurtech.review.domain.Vote;
import com.insurtech.review.repositories.ProductRepository;
import com.insurtech.review.repositories.UserRepository;
import com.insurtech.review.repositories.VoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final VoteRepository voteRepository;

    public VoteServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getVote(Long id) {
        return voteRepository.findById(id).orElse(null);
    }

    @Override
    public double getVoteAverage(Long productId) {
        return voteRepository.findConfirmedAverage(productId);
    }

    @Override
    @Transactional
    public Vote saveVote(Long productId, Long userId, int value) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Vote vote = null;
        if (user != null && product != null && product.isSaveVote()) {
            vote = new Vote();
            vote.setValue(value);
            vote.setProduct(product);
            vote.setUser(user);
            voteRepository.save(vote);
        }
        return vote;
    }

    @Override
    @Transactional
    public Vote updateVoteStatus(Long id, boolean status) {
        Optional<Vote> voteOptional = voteRepository.findById(id);
        Vote vote = null;
        if (voteOptional.isPresent()) {
            vote = voteOptional.get();
            if (!vote.isConfirmed()) {
                vote.setConfirmed(status);
                voteRepository.save(vote);
            }
        }
        return vote;
    }
}
