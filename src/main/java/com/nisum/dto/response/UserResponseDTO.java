package com.nisum.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {

    private UUID id;

    private String name;

    private String email;

    private String password;

    private String token;

    private List<PhoneResponseDTO> phones;

}
