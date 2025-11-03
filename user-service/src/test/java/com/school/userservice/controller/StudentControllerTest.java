package com.school.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.userservice.dto.StudentDTO;
import com.school.userservice.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper();
    }

    private StudentDTO createValidStudentDTO(String username, String email) {
        StudentDTO dto = new StudentDTO();
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setStudentClass("10A");
        dto.setGender("Female");    
        return dto;
    }

    @Test
    public void testCreateStudent() throws Exception {
        StudentDTO studentDTO = createValidStudentDTO("shipra", "shipra@example.com");

        doNothing().when(studentService).save(any(StudentDTO.class));

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMyProfile() throws Exception {
        StudentDTO studentDTO = createValidStudentDTO("shipra", "shipra@example.com");

        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.getName()).thenReturn("shipra");
        when(studentService.getStudentByUsername("shipra")).thenReturn(studentDTO);

        mockMvc.perform(get("/students/dashboard")
                .principal(mockAuth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("shipra"))
                .andExpect(jsonPath("$.email").value("shipra@example.com"))
                .andExpect(jsonPath("$.studentClass").value("10A"))
                .andExpect(jsonPath("$.gender").value("Female"));
    }

    @Test
    public void testUpdateMyProfile() throws Exception {
        StudentDTO inputDTO = createValidStudentDTO("shipra", "shipra1@example.com");
        StudentDTO updatedDTO = createValidStudentDTO("shipra", "shipra1@example.com");

        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.getName()).thenReturn("shipra");
        when(studentService.updateStudentProfile(eq("shipra"), any(StudentDTO.class))).thenReturn(updatedDTO);

        mockMvc.perform(put("/students/update")
                .principal(mockAuth)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("shipra"))
                .andExpect(jsonPath("$.email").value("shipra1@example.com"))
                .andExpect(jsonPath("$.studentClass").value("10A"))
                .andExpect(jsonPath("$.gender").value("Female"));
    }
}
