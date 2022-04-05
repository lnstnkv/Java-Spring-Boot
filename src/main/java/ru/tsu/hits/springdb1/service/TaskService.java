package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ProjectService projectService;
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Transactional
    public TaskDto save(CreateUpdateTasksDto createUpdateTasksDto) {

        var projectTasks = projectService.getProjectEntityById(createUpdateTasksDto.getProject_id());
        var createdUser=userService.getUserEntityById(createUpdateTasksDto.getUsers_id());
        TaskEntity taskEntity = TaskDtoConverter.converterDtoToEntity(createUpdateTasksDto, projectTasks,createdUser);
        taskEntity = taskRepository.save(taskEntity);
        return TaskDtoConverter.converterEntityWithProjectToDto(taskEntity, projectTasks,createdUser);
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


}
