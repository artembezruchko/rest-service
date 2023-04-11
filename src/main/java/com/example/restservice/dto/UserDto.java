package com.example.restservice.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long  id;
    private String testName;
    private String photosId;
    private String email;
    private String password;
    private String data;
    private boolean isUsed;
}
