package com.code.glancer.interview.scheduler.service.impl;

import com.code.glancer.interview.scheduler.domain.User;
import com.code.glancer.interview.scheduler.dto.UserDto;
import com.code.glancer.interview.scheduler.exception.ResourceNotFoundException;
import com.code.glancer.interview.scheduler.mapper.UserMapper;
import com.code.glancer.interview.scheduler.repository.UserRepository;
import com.code.glancer.interview.scheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User userEntity = userRepository.save(user);
        userDto = userMapper.toUserDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("Resource not Found", HttpStatus.NOT_FOUND));
        UserDto userDto = userMapper.toUserDto(user);
        return userDto;
    }

    @Override
    public User findUserByEmailId(String emailId) {
        User user = userRepository.findByEmailId(emailId).orElseThrow(() -> new ResourceNotFoundException("User not found ", HttpStatus.NOT_FOUND));
        return user;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = userMapper.toUserDtos(users);
        return userDtos;
    }
}
