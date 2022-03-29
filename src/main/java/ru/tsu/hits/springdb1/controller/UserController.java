package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody CreateUserDto createUserDto) {

        return userService.save(createUserDto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable UUID id) {
        return userService.getById(id.toString());
    }
   /* @GetMapping()
    public List<UserDto> getAll() {
        return userService;
    }
    */
}
