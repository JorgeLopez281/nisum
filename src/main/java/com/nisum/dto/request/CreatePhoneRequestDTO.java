package com.nisum.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneRequestDTO {

    @JsonProperty("number")
    @NotEmpty(message = "Number cannot be empty")
    private String number;

    @JsonProperty("city_code")
    @Positive(message = "City code must be greater than zero")
    private Integer cityCode;

    @JsonProperty("country_code")
    @Positive(message = "Country code must be greater than zero")
    private Integer countryCode;

}
