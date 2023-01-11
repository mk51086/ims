package com.ims.controller;

import com.ims.constant.Controller;
import com.ims.constant.Util;
import com.ims.dto.LoginRequest;
import com.ims.dto.SignUpRequest;
import com.ims.dto.BasicResponse;
import com.ims.dto.TokenContainingResponse;
import com.ims.dto.MeResponse;
import com.ims.dto.UserInfo;
import com.ims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenContainingResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        String token = userService.loginAndGenerateToken(loginRequest);

        TokenContainingResponse response = new TokenContainingResponse(HttpStatus.OK, Controller.LOG_IN_SUCCESS_MESSAGE, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);

        BasicResponse response = new BasicResponse(HttpStatus.OK, Controller.SIGN_UP_SUCCESS_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/me")
    public ResponseEntity<MeResponse> me(@RequestHeader(value = Util.AUTHORIZATION) String token){
        UserInfo user = userService.me(token);
        MeResponse response = new MeResponse(HttpStatus.OK, Controller.LOG_IN_SUCCESS_MESSAGE, user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
