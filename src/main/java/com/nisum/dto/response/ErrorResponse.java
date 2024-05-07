package com.nisum.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @JsonProperty("status_code")
    private String statusCode;
    @JsonProperty("title")
    private String title;
    @JsonProperty("detail")
    private String detail;
    @JsonProperty("date_time")
    private LocalDateTime dateTime;
}
