package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.ProjectNotFoundException;
import ru.tsu.hits.springdb1.repository.ProjectRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;
import ru.tsu.hits.springdb1.service.part1.Application;
import ru.tsu.hits.springdb1.csv.ProjectCsv;

import javax.validation.Valid;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public ProjectDto save(@Valid CreateUpdateProjectDto createUpdateProjectDto) {
        ProjectEntity projectEntity = ProjectDtoConverter.converterDtoToEntity(createUpdateProjectDto);
        projectEntity = projectRepository.save(projectEntity);
        return ProjectDtoConverter.converterEntityToDto(projectEntity, getTasksByProject(projectEntity));

    }

    @Transactional(readOnly = true)
    public ProjectEntity getProjectEntityById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Проект с id" + id + " не найден"));

    }


    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(String id) {
        ProjectEntity projectEntity = getProjectEntityById(id);
        return ProjectDtoConverter.converterEntityToDto(projectEntity, getTasksByProject(projectEntity));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByProject(ProjectEntity projectEntity) {
        return taskRepository.findByProject(projectEntity);
    }

    @Transactional(readOnly = true)
    public List<ProjectDto> getCsvToDto() {

        var csvStream = Application.class.getResourceAsStream("/projects.csv");
        var projects = new CsvToBeanBuilder<ProjectCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(ProjectCsv.class)
                .withSkipLines(1)
                .build()
                .parse();

        List<ProjectDto> projectList = projects
                .stream()
                .map(ProjectDtoConverter::converterScvToDto)
                .collect(Collectors.toList());


        return projectList;
    }

    public void saveCsv(@Valid List<ProjectDto> projectDto) {

        List<ProjectEntity> projectEntities = projectDto
                .stream()
                .map(ProjectDtoConverter::converterDtoToEntityForCsb)
                .collect(Collectors.toList());
        projectRepository.saveAll(projectEntities);

    }
}
