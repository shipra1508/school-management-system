package com.school.authservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.authservice.dto.SignUpDTO;
import com.school.authservice.enums.UserRole;
import com.school.authservice.exception.GlobalExceptionHandler;
import com.school.authservice.exception.UserAlreadyExistsException;
import com.school.authservice.service.SignupService;

public class SignupControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SignupService signupService;

    @InjectMocks
    private SignupController signupController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(signupController)
                .setControllerAdvice(new GlobalExceptionHandler()) 
                .build();
        objectMapper = new ObjectMapper();
    }

    private SignUpDTO createValidStudentDTO() {
        SignUpDTO dto = new SignUpDTO();
        dto.setUsername("shipra");
        dto.setEmail("shipra@example.com");
        dto.setPassword("securePass123");
        dto.setRole(UserRole.STUDENT);  
        return dto;
    }

    @Test
    public void testSignupSuccess() throws Exception {
        SignUpDTO signUpDTO = createValidStudentDTO();

        doNothing().when(signupService).signup(any(SignUpDTO.class), anyString());

        mockMvc.perform(post("/auth/signup")
                .header("Authorization", "Bearer mock-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testSignupFailure() throws Exception {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setUsername("existinguser");
        signUpDTO.setEmail("existing@example.com");
        signUpDTO.setPassword("securePass123");
        signUpDTO.setRole(UserRole.TEACHER); 

        doThrow(new UserAlreadyExistsException("User already exists")).when(signupService).signup(any(SignUpDTO.class), anyString());

        mockMvc.perform(post("/auth/signup")
                .header("Authorization", "Bearer mock-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("User already exists"));
    }

    @Test
    public void testSignupMissingAuthHeader() throws Exception {
        SignUpDTO signUpDTO = createValidStudentDTO();

        doNothing().when(signupService).signup(any(SignUpDTO.class), eq(null));

        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered successfully"));
    }
}
