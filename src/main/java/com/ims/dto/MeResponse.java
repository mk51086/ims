package com.ims.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeResponse {
    private HttpStatus httpStatus;
    private String message;
    private UserDTO userInfo;
}
