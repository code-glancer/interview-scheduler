package com.code.glancer.interview.scheduler.service;

import com.code.glancer.interview.scheduler.domain.User;
import com.code.glancer.interview.scheduler.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(Long id);

    User findUserByEmailId(String emailId);

    List<UserDto> getUsers();
}
