package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;
import ru.tsu.hits.springdb1.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public UserDto save(CreateUserDto createUserDto) {
        UserEntity userEntity = UserDtoConverter.converterDtoToEntity(createUserDto);
        userEntity = userRepository.save(userEntity);
        return UserDtoConverter.converterEntityToDto(userEntity, getTasksByUser(userEntity),getTasksByUserComments(userEntity));

    }

    public List<TaskEntity> getTasksByUser(UserEntity userEntity) {
        return taskRepository.findByCreatedUser(userEntity);
    }
    public List<CommentEntity> getTasksByUserComments(UserEntity userEntity) {
        return commentRepository.findByCreatedUserComments(userEntity);
    }
    @Transactional(readOnly = true)
    public UserEntity getUserEntityById(String id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("Пользователь с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public UserDto getUserDtoById(String id) {
        UserEntity userEntity=getUserEntityById(id);
        return UserDtoConverter.converterEntityToDto(userEntity,getTasksByUser(userEntity),getTasksByUserComments(userEntity));
    }


}
