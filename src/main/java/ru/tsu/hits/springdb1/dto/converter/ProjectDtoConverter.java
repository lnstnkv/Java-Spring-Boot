package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;

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

    public static ProjectDto converterEntityToDto(ProjectEntity projectEntity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setDescription(projectEntity.getDescription());
        projectDto.setId(projectEntity.getUuid());
        projectDto.setName(projectEntity.getName());
        projectDto.setDateCreate(projectEntity.getDateCreate());
        projectDto.setDateEdit(projectEntity.getDateEdit());
        return projectDto;
    }
}
