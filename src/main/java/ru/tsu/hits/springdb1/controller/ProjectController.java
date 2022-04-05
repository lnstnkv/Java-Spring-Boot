package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.service.ProjectService;
import ru.tsu.hits.springdb1.service.TaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectDto save( @Valid @RequestBody CreateUpdateProjectDto createUpdateProjectDto) {

        return projectService.save(createUpdateProjectDto);
    }
    @GetMapping(value = "/{id}")
    public ProjectDto getProjectById(@PathVariable String id) {
        return projectService.getProjectDtoById(id);
    }
}
