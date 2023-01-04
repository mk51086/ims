package com.ims.service;

import com.ims.entity.Request.User.LoginRequest;
import com.ims.entity.Request.User.SignUpRequest;
import com.ims.entity.Response.User.UserInfo;

public interface IUserService {
    void signUp(SignUpRequest signUpRequest);
    String loginAndGenerateToken(LoginRequest loginRequest) throws Exception;
    UserInfo me(String token);
}
