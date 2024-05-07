package com.nisum.service;

import com.nisum.common.EmailValidator;
import com.nisum.common.PasswordValidator;
import com.nisum.entity.PhoneEntity;
import com.nisum.entity.UserEntity;
import com.nisum.exception.EmailException;
import com.nisum.exception.PasswordException;
import com.nisum.repository.IPhoneRepository;
import com.nisum.repository.IUserRepository;
import com.nisum.dto.request.CreateUserRequestDTO;
import com.nisum.dto.response.CreatedUserResponseDTO;
import com.nisum.dto.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Transactional
    public CreatedUserResponseDTO createUser(CreateUserRequestDTO userDto) throws PasswordException, EmailException {
        validateUser(userDto);
        UserEntity user = createUserEntity(userDto);
        user = userRepository.save(user);
        List<PhoneEntity> phones = createUserPhones(userDto, user);
        phoneRepository.saveAll(phones);
        return mappingService.map(user, CreatedUserResponseDTO.class);
    }

    public List<UserResponseDTO> getUsers() {
        return mappingService.mapList(userRepository.findAll(), UserResponseDTO.class);
    }

    private void validateUser(CreateUserRequestDTO userDto) throws PasswordException, EmailException {
        if (!passwordValidator.isValidPassword(userDto.getPassword())) {
            throw new PasswordException(PasswordException.INVALID_PASSWORD);
        }
        if (!emailValidator.isValidEmail(userDto.getEmail())) {
            throw new EmailException(EmailException.INVALID_EMAIL);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailException(EmailException.EXISTS_EMAIL);
        }
    }

    private UserEntity createUserEntity(CreateUserRequestDTO userDto) {
        return UserEntity.builder()
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .timeCreated(LocalDateTime.now())
                .timeModified(LocalDateTime.now())
                .timeLastLogin(LocalDateTime.now())
                .isActive(true)
                .build();
    }

    private List<PhoneEntity> createUserPhones(CreateUserRequestDTO userDto, UserEntity user) {
        List<PhoneEntity> phones = mappingService.mapList(userDto.getPhones(), PhoneEntity.class);
        phones.forEach(phone -> {
            phone.setUser(user);
            phone.setIsActive(true);
        });
        return phones;
    }
}
