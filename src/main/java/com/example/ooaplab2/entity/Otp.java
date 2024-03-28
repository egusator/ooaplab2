package com.example.ooaplab2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "otp")
@AllArgsConstructor
@NoArgsConstructor
public class Otp {

    @Id
    private String email;

    @Column(name = "otp_value")
    private Integer otpValue;

}
