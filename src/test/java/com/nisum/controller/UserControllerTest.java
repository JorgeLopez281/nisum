package com.nisum.controller;

import com.nisum.common.JsonUtils;
import com.nisum.dto.request.CreatePhoneRequestDTO;
import com.nisum.dto.request.CreateUserRequestDTO;
import com.nisum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private static final String USER_CONTROLLER_PATH_URI = "/users";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() throws Exception {

        CreatePhoneRequestDTO phoneRequestDTO = CreatePhoneRequestDTO.builder()
                .number("3135826356")
                .cityCode(1)
                .countryCode(57)
                .build();

        CreateUserRequestDTO userDTO = CreateUserRequestDTO.builder()
                .name("Test user")
                .email("jlopez@gmail.com")
                .password("Waddiwas1*.02")
                .phones(List.of(phoneRequestDTO))
                .build();

        mockMvc.perform(post(USER_CONTROLLER_PATH_URI )
                        .content(JsonUtils.convertToJson(userDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(get(USER_CONTROLLER_PATH_URI )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
