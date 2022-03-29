package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import java.util.UUID;

public class TaskDtoConverter {

    public static TaskEntity converterDtoToEntity(CreateUpdateTasksDto dto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUuid(UUID.randomUUID().toString());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setHeader(dto.getHeader());
        taskEntity.setPriority(dto.getPriority());

        return taskEntity;
    }

    public static TaskDto converterEntityToDto(TaskEntity taskEntity) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setId(taskEntity.getUuid());
        taskDto.setHeader(taskEntity.getHeader());
        taskDto.setPriority(taskEntity.getPriority());
        return taskDto;
    }

}
