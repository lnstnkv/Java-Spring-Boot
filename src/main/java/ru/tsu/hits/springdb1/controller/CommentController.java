package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateComment;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.service.CommentService;
import ru.tsu.hits.springdb1.service.ProjectService;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto save(@RequestBody CreateUpdateComment createUpdateComment) {

        return commentService.save(createUpdateComment);
    }
    @GetMapping(value = "/{id}")
    public CommentDto getCommentById(@PathVariable String id) {
        return commentService.getCommentDtoById(id);
    }
}
