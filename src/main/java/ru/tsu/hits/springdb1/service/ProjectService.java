package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.ProjectNotFoundException;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.ProjectRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ProjectService {

    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    @Transactional
    public ProjectDto save(@Valid CreateUpdateProjectDto createUpdateProjectDto) {
      //  var createdUser=userService.getUserEntityById(createUpdateProjectDto);
        ProjectEntity projectEntity = ProjectDtoConverter.converterDtoToEntity(createUpdateProjectDto);
        projectEntity = projectRepository.save(projectEntity);
        return ProjectDtoConverter.converterEntityToDto(projectEntity,getTasksByProject(projectEntity));

    }
    @Transactional(readOnly = true)
    public ProjectEntity getProjectEntityById(String id){
        return projectRepository.findById(id)
                .orElseThrow(()->new ProjectNotFoundException("Проект с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(String id) {
        ProjectEntity projectEntity=getProjectEntityById(id);
        return ProjectDtoConverter.converterEntityToDto(projectEntity,getTasksByProject(projectEntity));
    }

    public List<TaskEntity> getTasksByProject(ProjectEntity projectEntity) {
        return taskRepository.findByProject(projectEntity);
    }

}
