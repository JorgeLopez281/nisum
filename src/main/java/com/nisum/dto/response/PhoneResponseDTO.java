package com.nisum.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponseDTO implements Serializable {

    private UUID id;

    private String number;

    @JsonProperty("city_code")
    private Integer cityCode;

    @JsonProperty("country_code")
    private Integer countryCode;

}
