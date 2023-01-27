package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserRepository {

    List<UserDto> getUsers();
    UserDto findUserById(Long id);

    UserDto createUser(UserDto userDto);

    UserDto editUser(Long id, UserDto userDto);

    void deleteUser(Long userId);
}
