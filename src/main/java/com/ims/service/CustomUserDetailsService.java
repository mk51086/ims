package com.ims.service;

import com.ims.entity.CustomUserDetails;
import com.ims.entity.User;
import com.ims.exception.User.UserNotExistException;
import com.ims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotExistException::new);

        return new CustomUserDetails(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                true, true, true, true
        );
    }
}
