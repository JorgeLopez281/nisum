package com.nisum.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name= "password", nullable = false)
    private String password;

    @Column(name="token", columnDefinition = "CHAR(36)", nullable = false)
    private String token;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "time_modified")
    private LocalDateTime timeModified;

    @Column(name = "time_last_login")
    private LocalDateTime timeLastLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<PhoneEntity> phones;

    @PrePersist
    private void generateToken() {
        if (token == null) {
            token = UUID.randomUUID().toString();
        }
    }

}

