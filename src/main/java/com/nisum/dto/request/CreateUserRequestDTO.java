package com.nisum.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO implements Serializable {

    @JsonProperty("name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @JsonProperty("email")
    @NotEmpty(message = "Name cannot be empty")
    @Email(message = "Email must be valid")
    private String email;

    @JsonProperty("password")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;

    @JsonProperty("phones")
    private List<CreatePhoneRequestDTO> phones;
}
