package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;
    private final TaskRepository taskRepository;
    //private final CommentRepository commentRepository;

    @Transactional
    public TaskDto save(CreateUpdateTasksDto createUpdateTasksDto){

        var createdUser=userService.getUserEntityById(createUpdateTasksDto.getUsers_id());
      //  var createdComment:

        System.out.println(createdUser);
        var entity = new TaskEntity(
                UUID.randomUUID().toString(),
                createUpdateTasksDto.getHeader(),
                createUpdateTasksDto.getDescription(),
                createUpdateTasksDto.getPriority(),
                createdUser
                );

        var savedEntity = taskRepository.save(entity);
        return new TaskDto(
                savedEntity.getUuid(),
                savedEntity.getDescription(),
                savedEntity.getHeader(),
                savedEntity.getPriority(),
                createdUser.getName()
        );
    }
    @Transactional(readOnly = true)
    public TaskEntity getTaskEntityById(String id){
        return taskRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("Задача с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(String id) {
        TaskEntity taskEntity=getTaskEntityById(id);
        return TaskDtoConverter.converterEntityToDto(taskEntity);
    }



}
