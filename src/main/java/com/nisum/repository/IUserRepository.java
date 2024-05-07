package com.nisum.repository;

import com.nisum.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);

}
