package ru.tsu.hits.springdb1.dto.converter;

import lombok.SneakyThrows;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.csv.UserCsv;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter.getCommentDtos;

public class UserDtoConverter {

    public static UserEntity converterDtoToEntity(CreateUserDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setDateCreate(dto.getDateCreate());
        userEntity.setDateEdit(dto.getDateEdit());
        userEntity.setRole(dto.getRole());
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(hashedPassword);

        return userEntity;
    }

    public static UserDto converterEntityToDto(UserEntity userEntity, List<TaskEntity> taskEntities, List<TaskEntity> taskEntitiesEdit, List<CommentEntity> commentEntities) {
        UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());
        userDto.setId(userEntity.getUuid());
        userDto.setEmail(userEntity.getEmail());
        userDto.setDateCreate(userEntity.getDateCreate());
        userDto.setDateEdit(userEntity.getDateEdit());
        userDto.setPassword(userEntity.getPassword());
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
        userDto.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());
        userDto.setCreateTasks(convertTasksToDto(userEntity.getCreateTasks()));
        userDto.setEditTasks(convertTasksToDto(userEntity.getEditTasks()));
        userDto.setComments(convertCommentToDto(userEntity.getComments()));
        return userDto;
    }

    public static UserDto converterEntityWithoutTasksToDtoForCsv(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());
        userDto.setId(userEntity.getUuid());
        userDto.setEmail(userEntity.getEmail());
        userDto.setDateCreate(userEntity.getDateCreate());
        userDto.setDateEdit(userEntity.getDateEdit());
        userDto.setRole(userEntity.getRole());
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
        return getCommentDtos(commentEntities);

    }

    @SneakyThrows
    public static UserDto converterScvToDto(UserCsv userCsv){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfCreate = null;
        Date dateOfEdit = null;
        dateOfCreate = formatter.parse(userCsv.getCreationDate());
        dateOfEdit = formatter.parse(userCsv.getEditDate());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userCsv.getPassword());
        UserDto userDto = new UserDto();
        userDto.setId(userCsv.getId());
        userDto.setName(userCsv.getName());
        userDto.setEmail(userCsv.getEmail());
        userDto.setDateEdit(dateOfEdit);
        userDto.setDateCreate(dateOfCreate);
        userDto.setRole(userCsv.getRole());
        userDto.setPassword(hashedPassword);
        return userDto;
    }
    public static List<UserEntity> converterDtoToEntityForScv(List<UserDto> dto) {


        List<UserEntity> result = new ArrayList<>();
        dto.forEach(element -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(element.getName());
            userEntity.setEmail(element.getEmail());
            userEntity.setUuid(element.getId());
            userEntity.setDateEdit(element.getDateEdit());
            userEntity.setPassword(element.getPassword());
            userEntity.setDateCreate(element.getDateCreate());
            userEntity.setRole(element.getRole());
            result.add(userEntity);
        });


        return result;
    }

}
