package com.school.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.authservice.dto.SignUpDTO;
import com.school.authservice.entity.User;
import com.school.authservice.mapper.UserMapper;
import com.school.authservice.repository.UserRepository;

@Service
public class SignupServiceImpl implements SignupService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public SignupServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}

	@Override
	public void signup(SignUpDTO request) {

		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}

		User user = userMapper.toUser(request);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

	}

}
