package com.example.restservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String testName;
    private String photosId;
    private String email;
    private String password;
    private String data;
    private boolean isUsed;
    private String error;
}
