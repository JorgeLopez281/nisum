package com.nisum.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.dto.request.CreateUserRequestDTO;
import com.nisum.dto.response.CreatedUserResponseDTO;
import com.nisum.validations.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MappingServiceTest {

    @Mock
    private MappingService mappingService;

    @BeforeEach
    void setUp() {
        mappingService = new MappingService();
    }

    @Test
    void testMap_CreateUserRequestDTO_to_CreatedUserResponseDTO() {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .name("Alice")
                .email("alice@example.com")
                .password("SecurePass123")
                .build();

        CreatedUserResponseDTO response = mappingService.map(request, CreatedUserResponseDTO.class);

        assertNotNull(response);
        // No hay campos comunes → todos null
        assertNull(response.getId());
        assertNull(response.getToken());
        assertNull(response.getIsActive());
    }

    @Test
    void testMapList_multipleRequests() {
        List<CreateUserRequestDTO> requests = Arrays.asList(
                CreateUserRequestDTO.builder().name("Bob").email("bob@test.com").password("Password1").build(),
                CreateUserRequestDTO.builder().name("Carol").email("carol@test.com").password("Password2").build()
        );

        List<CreatedUserResponseDTO> responses = mappingService.mapList(requests, CreatedUserResponseDTO.class);

        assertEquals(2, responses.size());
        assertNull(responses.get(0).getId());
        assertNull(responses.get(1).getId());
    }

    @Test
    void testObjectMapper_serialization() throws Exception {
        ObjectMapper om = MappingService.getObjectMapper();

        String json = om.writeValueAsString(CreateUserRequestDTO.builder()
                .name("Test")
                .email("test@test.com")
                .password("abc12345")
                .build());

        assertTrue(json.contains("test@test.com"));
        assertTrue(json.contains("Test"));
    }

    @Test
    void testObjectMapper_ignoreUnknownProperties() throws Exception {
        ObjectMapper om = MappingService.getObjectMapper();

        String json = "{\"name\":\"David\",\"email\":\"david@test.com\",\"password\":\"Pass1234\",\"unknown\":\"ignored\"}";

        CreateUserRequestDTO dto = om.readValue(json, CreateUserRequestDTO.class);

        assertEquals("David", dto.getName());
        assertEquals("david@test.com", dto.getEmail());
    }
}
