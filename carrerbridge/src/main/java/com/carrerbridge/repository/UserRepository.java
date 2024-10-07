package com.carrerbridge.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.carrerbridge.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);
}
