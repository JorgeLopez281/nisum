package com.nisum.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedUserResponseDTO {

    private UUID id;

    private String token;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("created")
    private LocalDateTime timeCreated;

    @JsonProperty("modified")
    private LocalDateTime timeModified;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

}
