package com.carrerbridge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrerbridge.dto.LoginDTO;
import com.carrerbridge.dto.ResponseDTO;
import com.carrerbridge.dto.UserDTO;
import com.carrerbridge.exception.CarrerBridgeException;
import com.carrerbridge.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserAPI {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO>registerUser(@RequestBody @Valid UserDTO userDTO)throws CarrerBridgeException{
		userDTO = userService.registerUser(userDTO);
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO>loginUser(@RequestBody @Valid LoginDTO loginDTO)throws CarrerBridgeException{
		
		return new ResponseEntity<>(userService.loginUser(loginDTO), HttpStatus.OK);
	}
	
	@PostMapping("/sendOtp/{email}")
	public ResponseEntity<ResponseDTO> sendOtp(@PathVariable @Email(message="{user.email.invalid}") String email) {
	    try {
	        userService.sendOTP(email);
	        return new ResponseEntity<>(new ResponseDTO("OTP sent successfully."), HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(new ResponseDTO("Failed to send OTP: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/verifyOtp/{email}/{otp}")
	public ResponseEntity<ResponseDTO> verifyOtp(@PathVariable @Email(message="{user.email.invalid}") String email, @PathVariable @Pattern(regexp="^[0-9]{6}$" , message= "{otp.invalid}") String otp) throws CarrerBridgeException {
		 userService.verifyOTP(email, otp);
	        return new ResponseEntity<>(new ResponseDTO("OTP Verified Sucessfully"), HttpStatus.OK);
	}
	
	@PostMapping("/changePass")
	public ResponseEntity<ResponseDTO>changePassword(@RequestBody @Valid LoginDTO loginDTO)throws CarrerBridgeException{
		return new ResponseEntity<>(userService.changePassword(loginDTO), HttpStatus.OK);
	}
}
