package com.ims.service;

import com.ims.dto.LoginRequest;
import com.ims.dto.SignUpRequest;
import com.ims.dto.UserInfo;
import com.ims.enums.UserStatus;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    String loginAndGenerateToken(LoginRequest loginRequest) throws Exception;
    UserInfo me(String token);

    void changeUserStatus (Long userId, UserStatus status);
}
