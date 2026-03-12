package org.limir.universitylecturer.service;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.UserRequest;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.mappers.RoleMapper;
import org.limir.universitylecturer.mappers.UserMapper;
import org.limir.universitylecturer.model.Role;
import org.limir.universitylecturer.repository.RoleRepository;
import org.limir.universitylecturer.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public boolean checkPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public UserResponse saveUser(UserRequest userRequest) {
        var user = userMapper.userDTOToUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    public List<UserResponse> findAllUsers() {
        return userRepository
                .findAll(Sort.by(Sort.Direction.DESC, "name"))
                .stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserResponse)
                .orElse(null);
    }

    public UserResponse findUserByName(String name) {
        return userRepository
                .findByName(name)
                .map(userMapper::userToUserResponse)
                .orElse(null);
    }

    public UserResponse findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .map(userMapper::userToUserResponse)
                .orElse(null);
    }

    public Long deleteUserById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    public String deleteUserByName(String name) {
        userRepository.deleteUserByName(name).orElse(null);
        return name;
    }

    public List<UserResponse> findUsersByNameEmailRole (String name, String email, Long roleId) {
        return userRepository.
                findUsersByNameEmailRole(name, email, roleId)
                .stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    public void changeUserRole(String name) {
        var user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден: " + name));

        String newRoleName = user.getRole().getName().equals("ADMIN") ? "USER" : "ADMIN";

        Role newRole = roleRepository.findByName(newRoleName)
                .orElseThrow(() -> new RuntimeException("Роль не найдена: " + newRoleName));

        user.setRole(newRole);

        userRepository.save(user);
    }

    public UserResponse authenticate(String email, String password) {
        var user = userRepository.findByEmail(email).orElse(null);

        if (user != null && checkPassword(password, user.getPassword())) {
            return userMapper.userToUserResponse(user);
        }

        return null;
    }
}
