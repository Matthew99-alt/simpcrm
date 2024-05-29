package com.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String login;

    private String firstName;

    private String secondName;

    private String middleName;

    private String email;

    private Long phone;

    private String address;

    private String role;

    private String city;
}
