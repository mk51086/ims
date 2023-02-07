package com.ims.service.impl;

import com.ims.entity.CustomUserDetails;
import com.ims.dto.LoginRequest;
import com.ims.dto.SignUpRequest;
import com.ims.dto.UserDTO;
import com.ims.entity.User;
import com.ims.enums.UserStatus;
import com.ims.exception.EmptyValueExistException;
import com.ims.exception.User.*;
import com.ims.repository.UserRepository;
import com.ims.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements com.ims.service.UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
        UserStatus status = Optional.ofNullable(signUpRequest.getStatus()).orElseThrow(EmptyValueExistException::new);

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
                address,
                UserStatus.InActive
        );
        logger.info("Created User " + newUser);
        userRepository.save(newUser);
    }

    public String loginAndGenerateToken(LoginRequest loginRequest) throws Exception {
        String email = Optional.ofNullable(loginRequest.getEmail()).orElseThrow(EmptyValueExistException::new);
        String password = Optional.ofNullable(loginRequest.getPassword()).orElseThrow(EmptyValueExistException::new);

        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent()){
            throw new UserNotExistException();
        }

        User userData = user.get();
        if(userData.getStatus() != UserStatus.Active){
            throw new UserNotActivatedExecption();
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

    public UserDTO me(String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String email = userDetails.getEmail();

        String parsedToken = token.substring(7);

        boolean isValidateToken = jwtUtil.validateToken(parsedToken, email);

        if (!isValidateToken) {
            throw new UserNotExistException();
        }

        UserDTO userInfo;

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UserNotExistException();
        }

        userInfo = new UserDTO(
                user.get().getUserId(),
                user.get().getEmail(),
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getAddress(),
                user.get().getStatus()
        );

        return userInfo;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new UserNotExistException();
        }
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user.getUserId(), user.getEmail(), user.getFirstName(),user.getLastName(),user.getAddress(),user.getStatus()));
        }
        return userDTOS;
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO userInfo;

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotExistException();
        }

        userInfo = new UserDTO(
                user.get().getUserId(),
                user.get().getEmail(),
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getAddress(),
                user.get().getStatus()
        );

        return userInfo;
    }

    @Override
    public void changeUserStatus(Long userId,UserStatus status) {
        User user = userRepository.findById(userId).orElseThrow(() ->new UsernameNotFoundException("User not found with Id"+ userId));
        user.setStatus(status);
        userRepository.save(user);
    }
}
