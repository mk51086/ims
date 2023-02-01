package com.ims.dto;

import com.ims.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String address;
    private UserStatus status;
}
