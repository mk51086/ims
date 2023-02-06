package com.ims.service;

import com.ims.dto.LoginRequest;
import com.ims.dto.SignUpRequest;
import com.ims.dto.UserDTO;
import com.ims.enums.UserStatus;

import java.util.List;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    String loginAndGenerateToken(LoginRequest loginRequest) throws Exception;
    UserDTO me(String token);
    List<UserDTO> getUsers();
    UserDTO getUserById(Long id);


    void changeUserStatus (Long userId, UserStatus status);
}
