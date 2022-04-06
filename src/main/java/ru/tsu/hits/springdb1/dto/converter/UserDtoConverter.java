package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDtoConverter {

    public static UserEntity converterDtoToEntity(CreateUserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setDateCreate(dto.getDateCreate());
        userEntity.setDateEdit(dto.getDateEdit());
        userEntity.setRole(dto.getRole());
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());

        return userEntity;
    }

    public static UserDto converterEntityToDto(UserEntity userEntity, List<TaskEntity> taskEntities, List<TaskEntity> taskEntitiesEdit, List<CommentEntity> commentEntities) {
        UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());
        userDto.setId(userEntity.getUuid());
        userDto.setEmail(userEntity.getEmail());
        userDto.setDateCreate(userEntity.getDateCreate());
        userDto.setDateEdit(userEntity.getDateEdit());
        userDto.setRole(userEntity.getRole());
        userDto.setCreateTasks(convertTasksToDto(taskEntities));
        userDto.setEditTasks(convertTasksToDto(taskEntitiesEdit));
        userDto.setComments(convertCommentToDto(commentEntities));
        return userDto;
    }
    public static UserDto converterEntityWithoutTasksToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());
        userDto.setId(userEntity.getUuid());
        userDto.setEmail(userEntity.getEmail());
        userDto.setDateCreate(userEntity.getDateCreate());
        userDto.setDateEdit(userEntity.getDateEdit());
        userDto.setRole(userEntity.getRole());
        userDto.setCreateTasks(convertTasksToDto(userEntity.getCreateTasks()));
        userDto.setEditTasks(convertTasksToDto(userEntity.getEditTasks()));
        userDto.setComments(convertCommentToDto(userEntity.getComments()));
        return userDto;
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

    private static List<CommentDto> convertCommentToDto(List<CommentEntity> commentEntities) {
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
