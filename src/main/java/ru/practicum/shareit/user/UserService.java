package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers() {
        return userRepository.getUsers();
    }

    public UserDto findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public UserDto createUser(UserDto userDto) {
        return userRepository.createUser(userDto);
    }

    public UserDto editUser(Long id, UserDto userDto) {
        return userRepository.editUser(id, userDto);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }
}
