package com.nisum.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "time_modified")
    private LocalDateTime timeModified;

    @Column(name = "time_last_login")
    private LocalDateTime timeLastLogin;

    protected BaseEntity() {}

    protected BaseEntity(UUID id) {
        this.id = id;
    }

}
