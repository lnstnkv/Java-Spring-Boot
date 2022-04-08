package ru.tsu.hits.springdb1.dto.converter;

import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tsu.hits.springdb1.csv.CommentScv;
import ru.tsu.hits.springdb1.csv.UserCsv;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommentDtoConverter {

    public static CommentEntity converterDtoToEntity(CreateUpdateComment dto, UserEntity userCreated, List<TaskEntity> taskEntities) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUuid(UUID.randomUUID().toString());
        commentEntity.setDateCreate(dto.getDateCreate());
        commentEntity.setTasks(taskEntities);
        commentEntity.setDateEdit(dto.getDateEdit());
        commentEntity.setCreatedUserComments(userCreated);
        commentEntity.setText(dto.getText());


        return commentEntity;
    }

    public static CommentDto converterEntityWithUserToDto(CommentEntity commentEntity,UserEntity userCreated, List<TaskEntity> taskEntities) {

        CommentDto commentDto = new CommentDto();
        commentDto.setText(commentEntity.getText());
        commentDto.setUuid(commentEntity.getUuid());
        commentDto.setDateCreate(commentEntity.getDateCreate());
        commentDto.setUsers_id(userCreated.getName());
        commentDto.setTasks(convertTasksToDto(taskEntities));
        commentDto.setDateEdit(commentEntity.getDateEdit());
        return commentDto;
    }
    public static CommentDto converterEntityToDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(commentEntity.getText());
        commentDto.setUuid(commentEntity.getUuid());
        commentDto.setUsers_id(commentEntity.getCreatedUserComments().getUuid());
        commentDto.setDateCreate(commentEntity.getDateCreate());
        commentDto.setDateEdit(commentEntity.getDateEdit());
        commentDto.setTasks(convertTasksToDto(commentEntity.getTasks()));
        return commentDto;
    }
    public static GetCommentDto converterEntityToDtoWithoutCommentsInTasks(CommentEntity commentEntity) {
        GetCommentDto commentDto = new GetCommentDto();
        commentDto.setText(commentEntity.getText());
        commentDto.setUuid(commentEntity.getUuid());
        commentDto.setUsers_id(commentEntity.getCreatedUserComments().getUuid());
        commentDto.setDateCreate(commentEntity.getDateCreate());
        commentDto.setDateEdit(commentEntity.getDateEdit());
       // commentDto.setTasks(convertTasksToDto(commentEntity.getTasks()));
        return commentDto;
    }
    public static List<TaskDto> convertTasksToDto(List<TaskEntity> taskEntities) {
        List<TaskDto> result = new ArrayList<>();
        taskEntities.forEach(element -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(element.getUuid());
            taskDto.setCreator_id(element.getCreatedUser().getName());
            taskDto.setPerformer_id(element.getPerformerUser().getName());
            taskDto.setDateCreate(element.getDateCreate());
            taskDto.setDateEdit(element.getDateEdit());
            taskDto.setTimeEstimate(element.getTimeEstimate());
            taskDto.setProject_id(element.getProject().getName());
            taskDto.setDescription(element.getDescription());
            taskDto.setHeader(element.getHeader());
            taskDto.setPriority(element.getPriority());

            result.add(taskDto);
        });
        return result;
    }

    @SneakyThrows
    public static CreateUpdateComment converterScvToDto(CommentScv commentScv){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfCreate = null;
        Date dateOfEdit = null;
        dateOfCreate = formatter.parse(commentScv.getCreationDate());
        dateOfEdit = formatter.parse(commentScv.getEditDate());
        List<String> result = Arrays.asList(commentScv.getTasks().split(" "));
        CreateUpdateComment commentDto = new CreateUpdateComment();
        commentDto.setText(commentScv.getText());
        commentDto.setDateEdit(dateOfEdit);
        commentDto.setTasks_id(result);
        commentDto.setUsers_id(commentScv.getAuthor());
        commentDto.setDateCreate(dateOfCreate);
           return commentDto;
    }
}
