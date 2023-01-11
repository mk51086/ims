package com.ims.service;

import com.ims.dto.LoginRequest;
import com.ims.dto.SignUpRequest;
import com.ims.dto.UserInfo;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    String loginAndGenerateToken(LoginRequest loginRequest) throws Exception;
    UserInfo me(String token);
}
