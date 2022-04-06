package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class TaskService {

    private final ProjectService projectService;
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Transactional
    public TaskDto save(@Valid CreateUpdateTasksDto createUpdateTasksDto) {

        var projectTasks = projectService.getProjectEntityById(createUpdateTasksDto.getProject_id());
        var createdUser=userService.getUserEntityById(createUpdateTasksDto.getCreator_id());
        var performer=userService.getUserEntityById(createUpdateTasksDto.getPerformer_id());
        TaskEntity taskEntity = TaskDtoConverter.converterDtoToEntity(createUpdateTasksDto, projectTasks,createdUser,performer);
        taskEntity = taskRepository.save(taskEntity);
        return TaskDtoConverter.converterEntityWithProjectToDto(taskEntity, projectTasks,createdUser,performer);
    }

    @Transactional(readOnly = true)
    public TaskEntity getTaskEntityById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Задача с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(String id) {
        TaskEntity taskEntity = getTaskEntityById(id);
        return TaskDtoConverter.converterEntityToDto(taskEntity);
    }

    @Transactional
    public List<TaskEntity> getTasksEntityByPerformer(UserEntity userEntity) {
        return taskRepository.findByPerformerUser(userEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getTasksDtoByPerformer(String id) {
        var user=userService.getUserEntityById(id);
        List<TaskEntity> taskEntity = getTasksEntityByPerformer(user);
        return UserDtoConverter.convertTasksToDto(taskEntity);
    }


    @Transactional
    public List<TaskEntity> getTasksEntityByProject(ProjectEntity projectEntity) {
        return taskRepository.findByProject(projectEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getTasksDtoByProject(String id) {
        var project=projectService.getProjectEntityById(id);
        List<TaskEntity> taskEntity = getTasksEntityByProject(project);
        return UserDtoConverter.convertTasksToDto(taskEntity);
    }

}
