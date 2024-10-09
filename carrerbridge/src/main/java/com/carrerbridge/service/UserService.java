package com.carrerbridge.service;

import com.carrerbridge.dto.LoginDTO;
import com.carrerbridge.dto.ResponseDTO;
import com.carrerbridge.dto.UserDTO;
import com.carrerbridge.exception.CarrerBridgeException;





public interface UserService {
	public UserDTO registerUser(UserDTO userDTO) throws CarrerBridgeException;

	public UserDTO loginUser( LoginDTO loginDTO) throws CarrerBridgeException;

	public Boolean sendOTP(String email) throws Exception;

	public Boolean verifyOTP(String email, String otp) throws CarrerBridgeException;

	public ResponseDTO changePassword(LoginDTO loginDTO) throws CarrerBridgeException;
	
}
