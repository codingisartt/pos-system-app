package com.tnb.possystem.mapper;

import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User savedUser)  {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setFullName(savedUser.getFullName());
        userDto.setPassword(savedUser.getPassword());
        userDto.setRole(savedUser.getRole());
        userDto.setPhone(savedUser.getPhone());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setPhone(savedUser.getPhone());
        return userDto;
    }
}
