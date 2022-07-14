package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.AuthenticatingDto;
import com.makichanov.core.model.dto.UserDto;
import com.makichanov.core.model.entity.Role;
import com.makichanov.core.model.entity.User;
import com.makichanov.core.repository.RoleRepository;
import com.makichanov.core.repository.UserRepository;
import com.makichanov.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: Предлагаю как-нибудь разделить этот сервис на 2: сервис, который реализует UserDetailsService
//                                                      и сервис, независимый от Spring Security
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    // TODO: 7/14/22 Пустая строка!
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConversionService conversionService;

    /* TODO: рекомендую следующее:
                    public User find(Long id)
                    {
                        return userRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("User not found, requested id " + id));
                    }
    */
    @Override
    public UserDto find(Long id) {
        Optional<User> user = userRepository.findById(id);
        User item = user.orElseThrow(
                () -> new EntityNotFoundException("User not found, requested id " + id));
        return conversionService.convert(item, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> conversionService.convert(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(AuthenticatingDto authenticatingDto) {
        User user = conversionService.convert(authenticatingDto, User.class);
        Role role = roleRepository.findByName(DEFAULT_USER_ROLE);
        user.setRole(role);
        User persisted = userRepository.save(user);
        return conversionService.convert(persisted, UserDto.class);
    }

    @Override
    public UserDto delete(Long deleteId) {
        Optional<User> user = userRepository.findById(deleteId);
        User item = user.orElseThrow(
                () -> new EntityNotFoundException("User not found, requested id " + deleteId));
        userRepository.delete(item);
        return conversionService.convert(item, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
