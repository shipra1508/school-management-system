package com.school.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.userservice.bo.AdminBO;
import com.school.userservice.dto.AdminDTO;
import com.school.userservice.entity.Admin;
import com.school.userservice.exception.AdminAlreadyExistsException;
import com.school.userservice.exception.AdminNotFoundException;
import com.school.userservice.mapper.AdminMapper;
import com.school.userservice.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public AdminDTO getAdminByUsername(String username) {
		Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new AdminNotFoundException(username));
		return adminMapper.toDTO(admin);
	}

	@Override
	public AdminDTO updateAdminProfile(String username, AdminDTO dto) {
		Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new AdminNotFoundException(username));

		AdminBO bo = adminMapper.toBO(dto);
		adminMapper.updateEntityFromBO(bo, admin);
		Admin updated = adminRepository.save(admin);
		return adminMapper.toDTO(updated);
	}

	@Override
	public void save(AdminDTO adminDTO) {
		if (adminRepository.findByUsername(adminDTO.getUsername()).isPresent()) {
			throw new AdminAlreadyExistsException(adminDTO.getUsername());
		}
		Admin admin = new Admin();
		admin.setUsername(adminDTO.getUsername());
		admin.setEmail(adminDTO.getEmail());
		admin.setGender(adminDTO.getGender());
		admin.setPhone(adminDTO.getPhone());

		System.out.println("Saving admin: " + admin.getUsername());
		adminRepository.save(admin);
	}

}
