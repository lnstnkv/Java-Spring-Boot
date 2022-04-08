package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.*;
import ru.tsu.hits.springdb1.service.CommentService;
import ru.tsu.hits.springdb1.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto save( @Valid @RequestBody CreateUpdateComment createUpdateComment) {

        return commentService.save(createUpdateComment);
    }

    @PostMapping(value = "/save")
    public void saveScv() {
        List<CreateUpdateComment> tasks = commentService.getCsvToDto();
        tasks.forEach(commentService::save);
    }


    @GetMapping(value = "/{id}")
    public CommentDto getCommentById(@PathVariable String id) {
        return commentService.getCommentDtoById(id);
    }

   /* @GetMapping(value = "/name/{id}")
    public CommentDto getCommentByName(@PathVariable String id) {
        return commentService.getCommentDtoByName(id);
    }

    */

}
