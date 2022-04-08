package ru.tsu.hits.springdb1.dto.converter;

import lombok.SneakyThrows;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateTasksDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.csv.TaskCsv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskDtoConverter {

    public static TaskEntity converterDtoToEntity(CreateUpdateTasksDto dto, ProjectEntity project, UserEntity userCreated, UserEntity performerUser) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUuid(UUID.randomUUID().toString());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setHeader(dto.getHeader());
        taskEntity.setDateEdit(dto.getDateEdit());
        taskEntity.setDateCreate(dto.getDateCreate());
        taskEntity.setPerformerUser(performerUser);
        taskEntity.setProject(project);
        taskEntity.setCreatedUser(userCreated);
        taskEntity.setTimeEstimate(dto.getTimeEstimate());
        taskEntity.setPriority(dto.getPriority());

        return taskEntity;
    }

    public static TaskEntity converterDtoToEntityForScv(TaskDto dto, ProjectEntity project, UserEntity userCreated, UserEntity performerUser) {


        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUuid(dto.getId());
        taskEntity.setDescription(dto.getDescription());
        taskEntity.setHeader(dto.getHeader());
        taskEntity.setDateEdit(dto.getDateEdit());
        taskEntity.setDateCreate(dto.getDateCreate());
        taskEntity.setPerformerUser(performerUser);
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
        taskDto.setPerformer_id(taskEntity.getPerformerUser().getName());
        taskDto.setCreator_id(taskEntity.getCreatedUser().getName());
        taskDto.setDateEdit(taskEntity.getDateEdit());
        taskDto.setProject_id(taskEntity.getProject().getUuid());
        taskDto.setComments(convertCommentToDto(taskEntity.getComments()));
        taskDto.setDateCreate(taskEntity.getDateCreate());
        taskDto.setTimeEstimate(taskEntity.getTimeEstimate());
        return taskDto;
    }
    public static TaskDto converterEntityToDtoForCsv(TaskEntity taskEntity) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setId(taskEntity.getUuid());
        taskDto.setHeader(taskEntity.getHeader());
        taskDto.setPriority(taskEntity.getPriority());
        taskDto.setProject_id(taskEntity.getProject().getUuid());

        return taskDto;
    }


    @SneakyThrows
    public static TaskDto converterScvToDto(TaskCsv taskCsv){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateOfCreate = null;
        Date dateOfEdit = null;
            dateOfCreate = formatter.parse(taskCsv.getCreationDate());
            dateOfEdit = formatter.parse(taskCsv.getEditDate());

        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskCsv.getText());
        taskDto.setId(taskCsv.getId());
        taskDto.setPriority(taskCsv.getPriority());
        taskDto.setPerformer_id(taskCsv.getPerformer());
        taskDto.setCreator_id(taskCsv.getAuthor());
        taskDto.setHeader(taskCsv.getHeader());
        taskDto.setTimeEstimate(taskCsv.getGrade());
        taskDto.setDateEdit(dateOfEdit);
        taskDto.setDateCreate(dateOfCreate);
        taskDto.setProject_id(taskCsv.getProject_id());
        return taskDto;
    }

    public static TaskDto converterEntityWithProjectToDto(TaskEntity taskEntity, ProjectEntity project, UserEntity userCreated, UserEntity performer) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setId(taskEntity.getUuid());
        taskDto.setHeader(taskEntity.getHeader());
        taskDto.setPriority(taskEntity.getPriority());
        taskDto.setProject_id(project.getUuid());
        taskDto.setCreator_id(userCreated.getName());
       // taskDto.setComments(convertCommentToDto(taskEntity.getComments()));
        taskDto.setPerformer_id(performer.getName());
        taskDto.setDateEdit(taskEntity.getDateEdit());
        taskDto.setDateCreate(taskEntity.getDateCreate());
        taskDto.setTimeEstimate(taskEntity.getTimeEstimate());
        return taskDto;
    }
    private static List<CommentDto> convertCommentToDto(List<CommentEntity> commentEntities) {
        return getCommentDtos(commentEntities);

    }

    static List<CommentDto> getCommentDtos(List<CommentEntity> commentEntities) {
        List<CommentDto> result = new ArrayList<>();
        commentEntities.forEach(element -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setUuid(element.getUuid());
            commentDto.setUsers_id(element.getCreatedUserComments().getName());
            commentDto.setText(element.getText());
            commentDto.setDateCreate(element.getDateCreate());
            commentDto.setDateEdit(element.getDateEdit());

            result.add(commentDto);
        });
        return result;
    }
}
