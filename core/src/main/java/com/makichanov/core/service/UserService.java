package com.makichanov.core.service;

import com.makichanov.core.entity.Role;
import com.makichanov.core.entity.User;
import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.repository.RoleRepository;
import com.makichanov.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Positive;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User find(Long id) {
        return findById(id);
    }

    public List<User> findAll(@Positive Long pageNum, @Positive Long pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum.intValue(), pageSize.intValue());
        return userRepository.findAll(pageRequest)
                .getContent();
    }

    public User create(User user) {
        Role role = roleRepository.findByName(DEFAULT_USER_ROLE);

        user.setRole(role);
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()));

        return userRepository.save(user);
    }

    public User update(Long id, User updated) {
        User user = findById(id);

        user.setUsername(updated.getUsername());
        user.setPassword(
                passwordEncoder.encode(
                        updated.getPassword()));

        return user;
    }

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
