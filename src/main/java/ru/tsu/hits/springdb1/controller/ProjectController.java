package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.service.ProjectService;
import ru.tsu.hits.springdb1.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;


    @PostMapping(value = "/save")
    public void saveScv() {
        List<ProjectDto> tasks = projectService.getCsvToDto();
        projectService.saveCsv(tasks);

    }

    @PostMapping
    public ProjectDto save( @Valid @RequestBody CreateUpdateProjectDto createUpdateProjectDto) {

        return projectService.save(createUpdateProjectDto);
    }
    @GetMapping(value = "/{id}")
    public ProjectDto getProjectById(@PathVariable String id) {
        return projectService.getProjectDtoById(id);
    }
}
