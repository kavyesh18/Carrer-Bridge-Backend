package com.carrerbridge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrerbridge.dto.LoginDTO;
import com.carrerbridge.dto.UserDTO;
import com.carrerbridge.exception.CarrerBridgeException;
import com.carrerbridge.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
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
}
