package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDtoConverter {

    public static ProjectEntity converterDtoToEntity(CreateUpdateProjectDto dto) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setUuid(UUID.randomUUID().toString());
        projectEntity.setName(dto.getName());
        projectEntity.setDateCreate(dto.getDateCreate());
        projectEntity.setDateEdit(dto.getDateEdit());
        projectEntity.setDescription(dto.getDescription());


        return projectEntity;
    }

    public static ProjectDto converterEntityToDto(ProjectEntity projectEntity, List<TaskEntity> taskEntities) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setDescription(projectEntity.getDescription());
        projectDto.setId(projectEntity.getUuid());
        projectDto.setName(projectEntity.getName());
        projectDto.setDateCreate(projectEntity.getDateCreate());
        projectDto.setDateEdit(projectEntity.getDateEdit());
        projectDto.setTasks(convertTasksToDto(taskEntities));
        return projectDto;
    }

    private static List<TaskDto> convertTasksToDto(List<TaskEntity> taskEntities) {
        List<TaskDto> result = new ArrayList<>();
        taskEntities.forEach(element -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(element.getUuid());
            taskDto.setCreator_id(element.getCreatedUser().getName());
            taskDto.setPerformer_id(element.getPerformerUser().getName());
            taskDto.setDescription(element.getDescription());
            taskDto.setHeader(element.getHeader());
            taskDto.setPriority(element.getPriority());

            result.add(taskDto);
        });
        return result;
    }
}
