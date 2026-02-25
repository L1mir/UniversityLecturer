package org.limir.universitylecturer.service;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.UserRequest;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.mappers.UserMapper;
import org.limir.universitylecturer.repository.UserRepository;
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
                .findAll()
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
}
