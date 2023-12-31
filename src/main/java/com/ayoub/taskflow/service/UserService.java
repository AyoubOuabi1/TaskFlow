package com.ayoub.taskflow.service;
import com.ayoub.taskflow.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long userId);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
     boolean existsById(Long userId);
    boolean isUserManager(Long userId);
}
