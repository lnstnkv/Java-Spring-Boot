package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.TaskDto;
import ru.tsu.hits.springdb1.dto.UserDto;
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

    public static UserDto converterEntityToDto(UserEntity userEntity, List<TaskEntity> taskEntities) {
        UserDto userDto = new UserDto();
        userDto.setName(userEntity.getName());
        userDto.setId(userEntity.getUuid());
        userDto.setEmail(userEntity.getEmail());
        userDto.setDateCreate(userEntity.getDateCreate());
        userDto.setDateEdit(userEntity.getDateEdit());
        userDto.setRole(userEntity.getRole());
        userDto.setTasks(convertTasksToDto(taskEntities));
        return userDto;
    }

    private static List<TaskDto> convertTasksToDto(List<TaskEntity> taskEntities) {
        List<TaskDto> result = new ArrayList<>();
        taskEntities.forEach(element -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setUsers_id(element.getUuid());
            taskDto.setUsers_id(element.getCreatedUser().getName());
            taskDto.setDescription(element.getDescription());
            taskDto.setHeader(element.getHeader());
            taskDto.setPriority(element.getPriority());

            result.add(taskDto);
        });
        return result;
    }
}
