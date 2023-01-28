package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.DuplicationException;
import ru.practicum.shareit.exception.IdNotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserRepositoryImpl implements UserRepository {
    private final List<User> userList;
    private final UserMapper userMapper;
    private Long id = 1L;

    public UserRepositoryImpl() {
        this.userList = new ArrayList<>();
        this.userMapper = new UserMapper();
    }

    @Override
    public List<UserDto> getUsers() {
        return userList.stream()
                .map(userMapper::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(Long id) {
        for (User u : userList) {
            if (Objects.equals(u.getId(), id)) {
                return userMapper.convertToUserDto(u);
            }
        }
        throw new IdNotFoundException("Пользователя с таким id нет");
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (checkFields(userDto.getName()) || checkFields(userDto.getEmail())) {
            throw new ValidationException("Имя пользователя и email должны быть заполнены");
        }

        for (User u : userList) {
            if (u.getEmail().equals(userDto.getEmail())) {
                throw new DuplicationException("Пользователь с таким email есть в памяти");
            }
        }

        userDto.setId(id);
        userList.add(userMapper.convertToUser(userDto));
        log.info("Пользователь добавлен: " + userDto);
        id++;
        return userDto;
    }

    @Override
    public UserDto editUser(Long id, UserDto userDto) {
        for (User u : userList) {
            if (Objects.equals(u.getId(), id)) {
                checkEmailDuplication(id, userDto);

                if (!checkFields(userDto.getName())) {
                    u.setName(userDto.getName());
                }
                if (!checkFields(userDto.getEmail())) {
                    u.setEmail(userDto.getEmail());
                }

                return userMapper.convertToUserDto(u);
            }
        }

        throw new IdNotFoundException("Пользователя с таким id нет");
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userList.removeIf(u -> Objects.equals(u.getId(), userId))) {
            throw new IdNotFoundException("Пользователя с таким id нет");
        }
    }

    private boolean checkFields(String field) {
        return field == null || field.isBlank();
    }

    private void checkEmailDuplication(Long id, UserDto userDto) {
        for (User d : userList) {
            if (!Objects.equals(d.getId(), id) && d.getEmail().equals(userDto.getEmail())) {
                throw new DuplicationException("Пользователь с таким email есть в памяти");
            }
        }
    }
}
