package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.FetchUsersDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.Role;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;
import ru.tsu.hits.springdb1.repository.UserRepository;
import ru.tsu.hits.springdb1.csv.UserCsv;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public UserDto save(CreateUserDto createUserDto) {
        UserEntity userEntity = UserDtoConverter.converterDtoToEntity(createUserDto);
        userEntity = userRepository.save(userEntity);
        return UserDtoConverter.converterEntityToDto(userEntity, getTasksByCreator(userEntity), getTasksByPerformer(userEntity), getTasksByUserComments(userEntity));

    }
    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByCreator(UserEntity userEntity) {
        return taskRepository.findByCreatedUser(userEntity);
    }
    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByPerformer(UserEntity userEntity) {
        return taskRepository.findByPerformerUser(userEntity);
    }

    @Transactional(readOnly = true)
    public List<CommentEntity> getTasksByUserComments(UserEntity userEntity) {
        return commentRepository.findByCreatedUserComments(userEntity);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserEntityById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id " + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public UserDto getUserDtoById(String id) {
        UserEntity userEntity = getUserEntityById(id);
        return UserDtoConverter.converterEntityToDto(userEntity, getTasksByCreator(userEntity), getTasksByPerformer(userEntity), getTasksByUserComments(userEntity));
    }

    @Transactional(readOnly = true)
    public UserDto findUserByName(String userName) {
        UserEntity userEntity = userRepository.findAllByName(userName);
        return UserDtoConverter.converterEntityWithoutTasksToDto(userEntity);
    }

    @Transactional(readOnly = true)
    public List<UserDto> fetchUsers(FetchUsersDto dto) {
        return userRepository.findAll(((root, query, criteriaBuilder) -> {
                    var predicates = new ArrayList<>();
                    dto.getFields().forEach((fieldName, fieldValue) -> {
                        switch (fieldName) {
                            case "name":
                            case "email":
                                predicates.add(criteriaBuilder.like(root.get(fieldName), '%' + fieldValue + '%'));
                                break;
                            case "role":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName), Role.valueOf(fieldValue)));
                                break;
                            case "dateCreate":
                            case "dateEdit":
                                try {
                                    predicates.add(criteriaBuilder.equal(root.get(fieldName),new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue)));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "createTask":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName),fieldName));
                            default:
                                throw new RuntimeException("Неверное имя поля: " + fieldName);
                        }
                    });
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }))
                .stream()
                .map(UserDtoConverter::converterEntityWithoutTasksToDto)
                .collect(Collectors.toList());
    }


    public List<UserDto> getCsvToDto() {

        var csvStream = Application.class.getResourceAsStream("/users.csv");
        var users = new CsvToBeanBuilder<UserCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(UserCsv.class)
                .withSkipLines(1)
                .build()
                .parse();

        List<UserDto> userList = users
                .stream()
                .map(UserDtoConverter::converterScvToDto)
                .collect(Collectors.toList());


        return userList;
    }
    @Transactional
    public void saveCsv(@Valid List<UserDto> createUpdateTasksDto) {

        List<UserEntity> userEntities = UserDtoConverter.converterDtoToEntityForScv(createUpdateTasksDto);

        userRepository.saveAll(userEntities);

    }
}
