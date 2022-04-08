package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.FetchUsersDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
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

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.getUserDtoById(id);
    }

    @PostMapping(value = "/fetch")
    public List<UserDto> fetchUsers(@RequestBody FetchUsersDto dto) {
        return userService.fetchUsers(dto);
    }

    @PostMapping(value = "/save")
    public void saveScv() {
        List<UserDto> tasks = userService.getCsvToDto();
        userService.saveCsv(tasks);

    }
}
