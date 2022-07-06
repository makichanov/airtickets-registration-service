package com.makichanov.core.service.impl;

import com.makichanov.core.exception.EntityNotFoundException;
import com.makichanov.core.model.dto.UserDto;
import com.makichanov.core.model.entity.User;
import com.makichanov.core.repository.UserRepository;
import com.makichanov.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ConversionService conversionService;

    @Override
    public UserDto find(Long id) {
        Optional<User> user = repository.findById(id);
        User item = user.orElseThrow(
                () -> new EntityNotFoundException("User not found, requested id " + id));
        return conversionService.convert(item, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(u -> conversionService.convert(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        User persisted = repository.save(user);
        return conversionService.convert(persisted, UserDto.class);
    }

    @Override
    public UserDto delete(Long deleteId) {
        Optional<User> user = repository.findById(deleteId);
        User item = user.orElseThrow(
                () -> new EntityNotFoundException("User not found, requested id " + deleteId));
        repository.delete(item);
        return conversionService.convert(item, UserDto.class);
    }

}
