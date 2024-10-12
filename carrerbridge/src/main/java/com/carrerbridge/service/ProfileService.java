package com.carrerbridge.service;

import com.carrerbridge.dto.ProfileDTO;
import com.carrerbridge.exception.CarrerBridgeException;

public interface ProfileService {
	public Long createProfile(String email) throws CarrerBridgeException;
	
	public ProfileDTO getProfile(Long id) throws CarrerBridgeException;
	
	public ProfileDTO updateProfile(ProfileDTO profileDTO) throws CarrerBridgeException;
}
