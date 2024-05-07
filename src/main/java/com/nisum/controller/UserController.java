package com.nisum.controller;

import com.nisum.exception.PasswordException;
import com.nisum.dto.request.CreateUserRequestDTO;
import com.nisum.dto.response.CreatedUserResponseDTO;
import com.nisum.dto.response.ErrorResponse;
import com.nisum.dto.response.UserResponseDTO;
import com.nisum.exception.EmailException;
import com.nisum.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Api(value = "UserController", tags = { "User Controller" })
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = CreatedUserResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class)})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedUserResponseDTO> createUser(
            @Valid @RequestBody CreateUserRequestDTO userDTO) throws PasswordException, EmailException {
        CreatedUserResponseDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @ApiOperation(value = "Get list of users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponseDTO[].class)})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
