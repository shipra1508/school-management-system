package com.school.userservice.service;

import com.school.userservice.dto.AdminDTO;

public interface AdminService {
	
	AdminDTO getAdminByUsername(String username);

	AdminDTO updateAdminProfile(String username, AdminDTO dto);

	void save(AdminDTO adminDTO);
}
