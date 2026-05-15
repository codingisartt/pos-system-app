package com.tnb.possystem.mapper;

import com.tnb.possystem.modal.User;
import com.tnb.possystem.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User savedUser)  {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setFullName(savedUser.getFullName());
        userDto.setRole(savedUser.getRole());
        userDto.setPhone(savedUser.getPhone());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setBranchId(savedUser.getStore()!=null?savedUser.getBranch().getId():null);
        userDto.setStoreId(savedUser.getBranch()!=null?savedUser.getStore().getId():null);
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setRole(userDto.getRole());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setLastLogin(userDto.getLastLogin());
        return user;
    }
}
