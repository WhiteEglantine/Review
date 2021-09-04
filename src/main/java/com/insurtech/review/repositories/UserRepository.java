package com.insurtech.review.repositories;

import com.insurtech.review.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
