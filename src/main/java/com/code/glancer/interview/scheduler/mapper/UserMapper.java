package com.code.glancer.interview.scheduler.mapper;

import com.code.glancer.interview.scheduler.domain.User;
import com.code.glancer.interview.scheduler.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(User user);

    User toUser(UserDto user);

    List<User> toUsers(List<UserDto> userDtos);

    List<UserDto> toUserDtos(List<User> users);

}
