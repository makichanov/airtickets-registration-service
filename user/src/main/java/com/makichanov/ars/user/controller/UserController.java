package com.makichanov.ars.user.controller;

import com.makichanov.ars.user.entity.User;
import com.makichanov.ars.user.mapper.UserMapper;
import com.makichanov.ars.user.model.UpdateUserRequest;
import com.makichanov.ars.user.model.UserDto;
import com.makichanov.ars.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User controller", description = "CRUD operations for User entity")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Read users by optional page parameters", description = "Returns list of found users")
    public ResponseEntity<List<UserDto>> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<User> users = userService.findAll(pageNum, pageSize);

        return ResponseEntity.ok(userMapper.toUserDtoList(users));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read user by id", description = "Returns found user")
    public ResponseEntity<UserDto> read(@PathVariable Long id) {
        User user = userService.find(id);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user by id", description = "Updates user, returns updated entity")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest dto) {
        User user = userMapper.toUser(dto);

        User updated = userService.update(id, user);

        return ResponseEntity.ok(userMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id", description = "Deletes user, returns deleted entity")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        User user = userService.delete(id);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

}
