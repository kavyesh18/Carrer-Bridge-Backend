package com.carrerbridge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carrerbridge.entity.Profile;

public interface ProfileRepository extends MongoRepository<Profile, Long> {
	
}
