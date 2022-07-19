package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.request.AuthenticateRequestDto;
import com.makichanov.core.entity.Role;
import com.makichanov.core.entity.User;
import com.makichanov.core.repository.RoleRepository;
import com.makichanov.core.repository.UserRepository;
import com.makichanov.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User find(Long id) {
        return findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(AuthenticateRequestDto authenticateRequestDto) {
        User user = conversionService.convert(authenticateRequestDto, User.class);
        Role role = roleRepository.findByName(DEFAULT_USER_ROLE);
        user.setRole(role);
        user.setPassword(
                passwordEncoder.encode(
                        authenticateRequestDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found, requested id " + id));
    }
}
