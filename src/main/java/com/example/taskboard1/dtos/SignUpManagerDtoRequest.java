package com.example.taskboard1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpManagerDtoRequest {
    private String name;
    private String email;
    private String password;

}
