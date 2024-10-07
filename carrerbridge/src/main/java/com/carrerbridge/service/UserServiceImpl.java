package com.carrerbridge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carrerbridge.dto.LoginDTO;
import com.carrerbridge.dto.UserDTO;
import com.carrerbridge.entity.User;
import com.carrerbridge.exception.CarrerBridgeException;
import com.carrerbridge.repository.UserRepository;
import com.carrerbridge.utility.Utilities;

@Service(value="userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserDTO userDTO)throws CarrerBridgeException {
    	Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
    	if(optional.isPresent()) throw new CarrerBridgeException("USER_FOUND");
    	
    	userDTO.setId(Utilities.getNextSequence("users"));
    	userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();
    }

	@Override
	public UserDTO loginUser(LoginDTO loginDTO) throws CarrerBridgeException {
		User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new CarrerBridgeException("USER_NOT_FOUND"));
		if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new CarrerBridgeException("INVALID_CREDENTIALS");
		return user.toDTO();
		
	}
}
