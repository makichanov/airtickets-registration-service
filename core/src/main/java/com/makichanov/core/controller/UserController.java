package com.makichanov.core.controller;

import com.makichanov.core.entity.User;
import com.makichanov.core.model.request.UpdateUserRequest;
import com.makichanov.core.model.response.UserDto;
import com.makichanov.core.service.UserService;
import com.makichanov.core.util.converter.ConversionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User controller", description = "CRUD operations for User entity")
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    @GetMapping
    @Operation(summary = "Read users by optional page parameters", description = "Returns list of found users")
    public ResponseEntity<List<UserDto>> readAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") @Positive Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") @Positive Long pageSize) {
        List<User> users = userService.findAll(pageNum, pageSize);

        return new ResponseEntity<>(ConversionUtils.toUserDtoList(users), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read user by id", description = "Returns found user")
    public ResponseEntity<UserDto> read(@PathVariable Long id) {
        User user = userService.find(id);

        return new ResponseEntity<>(conversionService.convert(user, UserDto.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user by id", description = "Updates user, returns updated entity")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest dto) {
        User user = conversionService.convert(dto, User.class);

        User updated = userService.update(id, user);

        return new ResponseEntity<>(conversionService.convert(updated, UserDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id", description = "Deletes user, returns deleted entity")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        User user = userService.delete(id);

        return new ResponseEntity<>(conversionService.convert(user, UserDto.class), HttpStatus.OK);
    }

}
