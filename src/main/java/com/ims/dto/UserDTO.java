package com.ims.dto;

import com.ims.enums.UserStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private UserStatus status;
}
