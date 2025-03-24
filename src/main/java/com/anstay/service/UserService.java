package com.anstay.service;

import com.anstay.dto.UserDTO;
import com.anstay.entity.User;
import com.anstay.enums.Role;
import com.anstay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Lấy danh sách người dùng
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getRole()
        )).collect(Collectors.toList());
    }

    // Tạo người dùng mới
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword()); // Cần mã hóa password nếu dùng bảo mật
        user.setRole(userDTO.getRole());

        User savedUser = userRepository.save(user);
        return new UserDTO(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getPassword(),
                savedUser.getRole()
        );
    }

    // Cập nhật người dùng
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(userDTO.getFullName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());

            User updatedUser = userRepository.save(user);
            return new UserDTO(
                    updatedUser.getId(),
                    updatedUser.getFullName(),
                    updatedUser.getEmail(),
                    updatedUser.getPhone(),
                    updatedUser.getPassword(),
                    updatedUser.getRole()
            );
        }
        return null;
    }

    // Xóa người dùng
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
