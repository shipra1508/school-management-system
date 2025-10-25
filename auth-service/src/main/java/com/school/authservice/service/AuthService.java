package com.school.authservice.service;

import com.school.authservice.dto.LoginDTO;

public interface AuthService {
	
	String authenticateAndGenerateToken(LoginDTO loginDTO);

}
