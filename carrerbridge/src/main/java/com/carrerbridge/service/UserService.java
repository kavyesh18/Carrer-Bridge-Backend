package com.carrerbridge.service;

import com.carrerbridge.dto.LoginDTO;
import com.carrerbridge.dto.UserDTO;
import com.carrerbridge.exception.CarrerBridgeException;



public interface UserService {
	public UserDTO registerUser(UserDTO userDTO) throws CarrerBridgeException;

	public UserDTO loginUser( LoginDTO loginDTO) throws CarrerBridgeException;
	
}
