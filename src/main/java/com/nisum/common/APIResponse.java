package com.nisum.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
public class APIResponse {

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    private int status = HttpStatus.BAD_REQUEST.value();

    private String error = HttpStatus.BAD_REQUEST.name();

    private Object message;

}
