package com.carrerbridge.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carrerbridge.dto.ProfileDTO;
import com.carrerbridge.entity.Profile;
import com.carrerbridge.exception.CarrerBridgeException;
import com.carrerbridge.repository.ProfileRepository;
import com.carrerbridge.utility.Utilities;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public Long createProfile(String email) throws CarrerBridgeException  {
		Profile profile = new Profile();
		profile.setId(Utilities.getNextSequence("profiles"));
		profile.setEmail(email);
		profile.setSkills(new ArrayList<>());
		profile.setExperiences(new ArrayList<>());
		profile.setCertifications(new ArrayList<>());
		profileRepository.save(profile);
		return profile.getId();
	}

	@Override
	public ProfileDTO getProfile(Long id) throws CarrerBridgeException {
		return profileRepository.findById(id).orElseThrow(()->new CarrerBridgeException("PROFILE_NOT_FOUND")).toDTO();
		
	}

	@Override
	public ProfileDTO updateProfile(ProfileDTO profileDTO) throws CarrerBridgeException {
		profileRepository.findById(profileDTO.getId()).orElseThrow(()->new CarrerBridgeException("PROFILE_NOT_FOUND"));
		profileRepository.save(profileDTO.toEntity());
		return profileDTO;
	}
		
}
