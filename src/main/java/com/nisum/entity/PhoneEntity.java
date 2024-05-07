package com.nisum.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="phones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "city_code", nullable = false)
    private Integer cityCode;

    @Column(name = "country_code", nullable = false)
    private Integer countryCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
