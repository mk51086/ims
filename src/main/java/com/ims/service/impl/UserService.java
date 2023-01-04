package com.ims.service.impl;

import com.ims.entity.CustomUserDetails;
import com.ims.entity.Request.User.LoginRequest;
import com.ims.entity.Request.User.SignUpRequest;
import com.ims.entity.Response.User.UserInfo;
import com.ims.entity.User;
import com.ims.exception.EmptyValueExistException;
import com.ims.exception.User.LoginFailException;
import com.ims.exception.User.PasswordNotMatchException;
import com.ims.exception.User.UserAlreadyExistException;
import com.ims.exception.User.UserNotExistException;
import com.ims.repository.UserRepository;
import com.ims.service.IUserService;
import com.ims.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public void signUp(SignUpRequest signUpRequest) {
        String email = Optional.ofNullable(signUpRequest.getEmail()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(signUpRequest.getPassword()).orElseThrow(EmptyValueExistException::new);
        String confirmPassword = Optional.ofNullable(signUpRequest.getConfirmPassword()).orElseThrow(EmptyValueExistException::new);
        String firstName = Optional.ofNullable(signUpRequest.getFirstName()).orElseThrow(EmptyValueExistException::new);
        String lastName = Optional.ofNullable(signUpRequest.getLastName()).orElseThrow(EmptyValueExistException::new);
        String address = Optional.ofNullable(signUpRequest.getAddress()).orElseThrow(EmptyValueExistException::new);

        boolean alreadyExist = userRepository.existsByEmail(email);

        if(alreadyExist){
            throw new UserAlreadyExistException();
        }

        if(!password.equals(confirmPassword)){
            throw new PasswordNotMatchException();
        }

        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(
                email,
                firstName,
                lastName,
                encodedPassword,
                address
        );
        userRepository.save(newUser);
    }

    public String loginAndGenerateToken(LoginRequest loginRequest) throws Exception {
        String email = Optional.ofNullable(loginRequest.getEmail()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(loginRequest.getPassword()).orElseThrow(EmptyValueExistException::new);

        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent()){
            throw new UserNotExistException();
        }

        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
            ));
        }catch(Exception e){
            throw new LoginFailException();
        }

        return jwtUtil.generateToken(email);
    }

    public UserInfo me(String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String email = userDetails.getEmail();

        String parsedToken = token.substring(7);

        boolean isValidateToken = jwtUtil.validateToken(parsedToken, email);

        if (!isValidateToken) {
            throw new UserNotExistException();
        }

        UserInfo userInfo = null;

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UserNotExistException();
        }

        userInfo = new UserInfo(
                user.get().getUserId(),
                user.get().getEmail(),
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getAddress()
        );

        return userInfo;
    }
}
