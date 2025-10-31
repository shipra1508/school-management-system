package com.school.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.school.authservice.dto.SignUpDTO;
import com.school.authservice.dto.StudentDTO;
import com.school.authservice.entity.User;
import com.school.authservice.mapper.UserMapper;
import com.school.authservice.repository.UserRepository;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private RestTemplate restTemplate;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public SignupServiceImpl(UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void signup(SignUpDTO request, String token) {
        // Check for duplicate email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Map and save user
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // Sync student to user-service if role is STUDENT
        if ("STUDENT".equalsIgnoreCase(user.getRole().name())) {
            System.out.println("Inside signup logic for: " + user.getUsername());
            System.out.println("Raw role enum: " + user.getRole());
            System.out.println("Role name string: " + user.getRole().name());

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setUsername(user.getUsername());
            studentDTO.setEmail(user.getEmail());
            studentDTO.setGender("NOT_SPECIFIED"); 
            studentDTO.setStudentClass("UNKNOWN");

            System.out.println("Syncing student to user-service: " + studentDTO.getUsername());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // ✅ Required
            // ❌ Do NOT set bearer token

            HttpEntity<StudentDTO> requestEntity = new HttpEntity<>(studentDTO, headers);

            try {
                restTemplate.postForEntity("http://localhost:8082/students", requestEntity, Void.class);
                System.out.println("Student sync successful");
            } catch (Exception e) {
                System.out.println("Student sync failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
