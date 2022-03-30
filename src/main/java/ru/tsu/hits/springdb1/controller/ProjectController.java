package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.service.ProjectService;
import ru.tsu.hits.springdb1.service.TaskService;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectDto save(@RequestBody CreateUpdateProjectDto createUpdateProjectDto) {

        return projectService.save(createUpdateProjectDto);
    }
}
