package com.carrerbridge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

import com.carrerbridge.entity.OTP;

public interface OTPRepository extends MongoRepository<OTP, String>{
	List<OTP>findByCreationTimeBefore(LocalDateTime expiry);
}
