package com.ims.entity;

import com.ims.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table", schema = "ims")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private UserStatus status;

/* kur t fillojm me punu me role, mos te hupum funksionalitetin e signup login
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
*/
    public User(
            String email,
            String firstName,
            String lastName,
            String password,
            String address,
            UserStatus status
    ){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.status = status;
    }
}
