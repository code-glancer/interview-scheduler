package com.code.glancer.interview.scheduler.dto;

import com.code.glancer.interview.scheduler.enums.Role;
import lombok.Data;

@Data
public class UserDto {

    private long id;

    private String name;

    private String address;

    private String emailId;

    private String password;

    private String mobile;

    private Role role;

    private String profileUrl;

    private String forgetToken;

    private Integer otp;

}
