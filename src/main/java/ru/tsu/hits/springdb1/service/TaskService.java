package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.TaskRepository;
import ru.tsu.hits.springdb1.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;
    private final TaskRepository taskRepository;


    /*@Transactional
    public TaskDto save(CreateUpdateTasksDto createUpdateTasksDto) {
        var createdUser=userService.getEntityToById(createUpdateTasksDto.getUserId());
        TaskEntity taskEntity = TaskDtoConverter.converterDtoToEntity(createUpdateTasksDto);
        taskEntity = taskRepository.save(taskEntity);
        return TaskDtoConverter.converterEntityToDto(taskEntity);

    }
     */
    @Transactional
    public TaskDto save(CreateUpdateTasksDto createUpdateTasksDto){
        var createdUser=userService.getEntityToById(createUpdateTasksDto.getUserId());
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
                createdUser.getUuid()
        );
    }


}
