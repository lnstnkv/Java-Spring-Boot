package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.*;

import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;
import ru.tsu.hits.springdb1.service.part1.Application;
import ru.tsu.hits.springdb1.csv.TaskCsv;

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
public class TaskService {

    private final ProjectService projectService;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Transactional
    public TaskDto save(@Valid CreateUpdateTasksDto createUpdateTasksDto) {

        var projectTasks = projectService.getProjectEntityById(createUpdateTasksDto.getProject_id());
        var createdUser = userService.getUserEntityById(createUpdateTasksDto.getCreator_id());
        var performer = userService.getUserEntityById(createUpdateTasksDto.getPerformer_id());
        TaskEntity taskEntity = TaskDtoConverter.converterDtoToEntity(createUpdateTasksDto, projectTasks, createdUser, performer);
        taskEntity = taskRepository.save(taskEntity);
        return TaskDtoConverter.converterEntityWithProjectToDto(taskEntity, projectTasks, createdUser, performer);
    }

    @Transactional(readOnly = true)
    public TaskEntity getTaskEntityById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Задача с id " + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public TaskDto getTaskDtoById(String id) {
        TaskEntity taskEntity = getTaskEntityById(id);
        return TaskDtoConverter.converterEntityToDto(taskEntity);
    }

    @Transactional
    public List<TaskEntity> getTasksEntityByPerformer(UserEntity userEntity) {
        return taskRepository.findByPerformerUser(userEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getTasksDtoByPerformer(String id) {
        var user = userService.getUserEntityById(id);
        List<TaskEntity> taskEntity = getTasksEntityByPerformer(user);
        return UserDtoConverter.convertTasksToDto(taskEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getTasksByComments(String name) {
        CommentEntity commentEntity = commentRepository.findByText(name);
        var commentDto = CommentDtoConverter.converterEntityToDto(commentEntity);
        return commentDto.getTasks();
    }

    @Transactional
    public List<TaskEntity> getTasksEntityByProject(ProjectEntity projectEntity) {
        return taskRepository.findByProject(projectEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getTasksDtoByProject(String id) {
        var project = projectService.getProjectEntityById(id);
        List<TaskEntity> taskEntity = getTasksEntityByProject(project);
        return UserDtoConverter.convertTasksToDto(taskEntity);
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getCsvToDto() {

        var csvStream = Application.class.getResourceAsStream("/tasks.csv");
        var tasks = new CsvToBeanBuilder<TaskCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(TaskCsv.class)
                .withSkipLines(1)
                .build()
                .parse();

        List<TaskDto> tasksList = tasks
                .stream()
                .map(TaskDtoConverter::converterScvToDto)
                .collect(Collectors.toList());


        return tasksList;
    }

    @Transactional
    public void saveCsv(@Valid TaskDto createUpdateTasksDto) {

        var projectTasks = projectService.getProjectEntityById(createUpdateTasksDto.getProject_id());
        var createdUser = userService.getUserEntityById(createUpdateTasksDto.getCreator_id());
        var performer = userService.getUserEntityById(createUpdateTasksDto.getPerformer_id());
        TaskEntity taskEntity = TaskDtoConverter.converterDtoToEntityForScv(createUpdateTasksDto, projectTasks, createdUser, performer);
        taskRepository.save(taskEntity);

    }


    @Transactional(readOnly = true)
    public List<TaskDto> fetchTasks(FetchTasksDto dto) {
        return taskRepository.findAll(((root, query, criteriaBuilder) -> {
                    var predicates = new ArrayList<>();
                    dto.getFields().forEach((fieldName, fieldValue) -> {
                        switch (fieldName) {
                            case "description":
                            case "header":
                                predicates.add(criteriaBuilder.like(root.get(fieldName), '%' + fieldValue + '%'));
                                break;
                            case "priority":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName), Priority.valueOf(fieldValue)));
                                break;
                            case "dateCreate":
                            case "dateEdit":
                                try {
                                    predicates.add(criteriaBuilder.equal(root.get(fieldName), new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue)));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "project_id":
                            case "performer_id":
                            case "creator_id":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName), fieldName));
                                break;
                            default:
                                throw new RuntimeException("Неверное имя поля: " + fieldName);
                        }
                    });
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }))
                .stream()
                .map(TaskDtoConverter::converterEntityToDto)
                .collect(Collectors.toList());
    }
}
