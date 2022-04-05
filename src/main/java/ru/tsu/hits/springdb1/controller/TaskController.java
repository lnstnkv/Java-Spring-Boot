package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.service.TaskService;
import ru.tsu.hits.springdb1.service.UserService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskDto save(@Valid @RequestBody CreateUpdateTasksDto createUpdateTasksDto) {

        return taskService.save(createUpdateTasksDto);
    }

    @GetMapping(value = "/{id}")
    public TaskDto getProjectById(@PathVariable String id) {
        return taskService.getTaskDtoById(id);
    }
}
