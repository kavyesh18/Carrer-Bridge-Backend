package com.carrerbridge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carrerbridge.dto.ProfileDTO;
import com.carrerbridge.exception.CarrerBridgeException;
import com.carrerbridge.service.ProfileService;


@RestController
@CrossOrigin
@Validated
@RequestMapping("/profiles")
public class ProfileAPI {
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) throws CarrerBridgeException {
	        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) throws CarrerBridgeException {
	        return new ResponseEntity<>(profileService.updateProfile(profileDTO), HttpStatus.OK);
	}
}
