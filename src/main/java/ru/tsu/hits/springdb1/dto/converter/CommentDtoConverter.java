package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateComment;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;

import java.util.UUID;

public class CommentDtoConverter {
    public static CommentEntity converterDtoToEntity(CreateUpdateComment dto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUuid(UUID.randomUUID().toString());
        commentEntity.setDateCreate(dto.getDateCreate());
        commentEntity.setDateEdit(dto.getDateEdit());
        commentEntity.setText(dto.getText());


        return commentEntity;
    }

    public static CommentDto converterEntityToDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(commentEntity.getText());
        commentDto.setUuid(commentEntity.getUuid());
        commentDto.setDateCreate(commentEntity.getDateCreate());
        commentDto.setDateEdit(commentEntity.getDateEdit());
        return commentDto;
    }
}
