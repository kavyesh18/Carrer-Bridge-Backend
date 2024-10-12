package com.carrerbridge.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.carrerbridge.dto.LoginDTO;
import com.carrerbridge.dto.ResponseDTO;
import com.carrerbridge.dto.UserDTO;
import com.carrerbridge.entity.OTP;
import com.carrerbridge.entity.User;
import com.carrerbridge.exception.CarrerBridgeException;
import com.carrerbridge.repository.OTPRepository;
import com.carrerbridge.repository.UserRepository;
import com.carrerbridge.utility.Data;
import com.carrerbridge.utility.Utilities;
import java.util.*;

import jakarta.mail.internet.MimeMessage;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;
    
    @Autowired
    private ProfileService profileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws CarrerBridgeException {
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
        if (optional.isPresent()) throw new CarrerBridgeException("USER_FOUND");
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        user = userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws CarrerBridgeException {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new CarrerBridgeException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new CarrerBridgeException("INVALID_CREDENTIALS");
        return user.toDTO();
    }

    @Override
    public Boolean sendOTP(String email) throws Exception {
       User user =  userRepository.findByEmail(email).orElseThrow(() -> new Exception("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOTP = Utilities.generateOtp();
        OTP otp = new OTP(email, genOTP, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(genOTP, user.getName()), true);
        mailSender.send(mm);
        return true;
    }

	@Override
	public Boolean verifyOTP(String email, String otp) throws CarrerBridgeException {
		OTP otpEntity = otpRepository.findById(email).orElseThrow(()->new CarrerBridgeException("OTP_NOT_FOUND"));
		if(!otpEntity.getOtpCode().equals(otp)) throw new CarrerBridgeException("OTP_IS_INCORRECT");
		return true;
		
	}

	@Override
	public ResponseDTO changePassword(LoginDTO loginDTO) throws CarrerBridgeException {
	User user =  userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new CarrerBridgeException("USER_NOT_FOUND"));
		user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
		userRepository.save(user);
		return new ResponseDTO("Password Changed Sucessfully");
	}
	
	@Scheduled(fixedRate = 60000)
	public void removeExpiredOTPs() {
	LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
	List<OTP>expiredOTPs = otpRepository.findByCreationTimeBefore(expiry);
	if(!expiredOTPs.isEmpty()) {
		otpRepository.deleteAll(expiredOTPs);
		System.out.println("Removed" +expiredOTPs.size()+ "expired OTPs");
	}
	}
}
