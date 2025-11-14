package com.school.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.school.authservice.dto.LoginDTO;
import com.school.authservice.exception.InvalidCredentialsException;
import com.school.authservice.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public String authenticateAndGenerateToken(LoginDTO loginDTO) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new InvalidCredentialsException("Invalid username or password");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
		return jwtUtil.generateToken(userDetails);
	}

}
