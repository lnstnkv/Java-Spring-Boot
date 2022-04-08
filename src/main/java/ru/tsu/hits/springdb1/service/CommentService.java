package ru.tsu.hits.springdb1.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.tsu.hits.springdb1.csv.CommentScv;
import ru.tsu.hits.springdb1.csv.TaskCsv;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.TaskDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.exception.CommentNotFoundException;
import ru.tsu.hits.springdb1.exception.ProjectNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.ProjectRepository;
import ru.tsu.hits.springdb1.service.part1.Application;

import javax.validation.Valid;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TaskService taskService;

    @Transactional
    public CommentDto save(@Valid CreateUpdateComment createUpdateComment) {
        var createdUser = userService.getUserEntityById(createUpdateComment.getUsers_id());

        List<TaskEntity> taskEntities = new ArrayList<>();
        for (String task : createUpdateComment.getTasks_id()) {
            taskEntities.add(taskService.getTaskEntityById(task));
        }
        CommentEntity commentEntity = CommentDtoConverter.converterDtoToEntity(createUpdateComment, createdUser, taskEntities);
        commentEntity = commentRepository.save(commentEntity);


        return CommentDtoConverter.converterEntityWithUserToDto(commentEntity, createdUser, taskEntities);

    }

    @Transactional(readOnly = true)
    public CommentEntity getCommentEntityById(String id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public CommentDto getCommentDtoByName(String name) {
        CommentEntity commentEntity = commentRepository.findByText(name);
        return CommentDtoConverter.converterEntityToDto(commentEntity);
    }

    @Transactional(readOnly = true)
    public CommentDto getCommentDtoById(String id) {
        CommentEntity commentEntity = getCommentEntityById(id);
        return CommentDtoConverter.converterEntityToDto(commentEntity);
    }


    @Transactional(readOnly = true)
    public List<CreateUpdateComment> getCsvToDto() {

        var csvStream = Application.class.getResourceAsStream("/comments.csv");
        var tasks = new CsvToBeanBuilder<CommentScv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(CommentScv.class)
                .withSkipLines(1)
                .build()
                .parse();

        List<CreateUpdateComment> tasksList = tasks
                .stream()
                .map(CommentDtoConverter::converterScvToDto)
                .collect(Collectors.toList());


        return tasksList;
    }
}
