package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.UUID;

public class TaskDtoConverter {

    public static TaskEntity converterDtoToEntity(CreateUpdateTasksDto dto, ProjectEntity project, UserEntity userCreated) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUuid(UUID.randomUUID().toString());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setHeader(dto.getHeader());
        taskEntity.setDateEdit(dto.getDateEdit());
        taskEntity.setDateCreate(dto.getDateCreate());
        taskEntity.setProject(project);
        taskEntity.setCreatedUser(userCreated);
        taskEntity.setTimeEstimate(dto.getTimeEstimate());
        taskEntity.setPriority(dto.getPriority());

        return taskEntity;
    }

    public static TaskDto converterEntityToDto(TaskEntity taskEntity) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setId(taskEntity.getUuid());
        taskDto.setHeader(taskEntity.getHeader());
        taskDto.setPriority(taskEntity.getPriority());
        taskDto.setUsers_id(taskEntity.getCreatedUser().getName());
        taskDto.setDateEdit(taskEntity.getDateEdit());
        taskDto.setProject_id(taskEntity.getProject().getUuid());
        taskDto.setDateCreate(taskEntity.getDateCreate());
        taskDto.setTimeEstimate(taskEntity.getTimeEstimate());
        return taskDto;
    }

    public static TaskDto converterEntityWithProjectToDto(TaskEntity taskEntity, ProjectEntity project,UserEntity userCreated) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setId(taskEntity.getUuid());
        taskDto.setHeader(taskEntity.getHeader());
        taskDto.setPriority(taskEntity.getPriority());
        taskDto.setProject_id(project.getUuid());
        taskDto.setUsers_id(userCreated.getName());
        taskDto.setDateEdit(taskEntity.getDateEdit());
        taskDto.setDateCreate(taskEntity.getDateCreate());
        taskDto.setTimeEstimate(taskEntity.getTimeEstimate());
        return taskDto;
    }

}
