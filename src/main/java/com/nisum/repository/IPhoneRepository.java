package com.nisum.repository;

import com.nisum.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneRepository extends JpaRepository<PhoneEntity, Integer> {
}
