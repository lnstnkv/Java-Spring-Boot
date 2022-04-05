package ru.tsu.hits.springdb1.dto.converter;

import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateComment;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.UUID;

public class CommentDtoConverter {
    public static CommentEntity converterDtoToEntity(CreateUpdateComment dto, UserEntity userCreated) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUuid(UUID.randomUUID().toString());
        commentEntity.setDateCreate(dto.getDateCreate());
        commentEntity.setDateEdit(dto.getDateEdit());
        commentEntity.setCreatedUserComments(userCreated);
        commentEntity.setText(dto.getText());


        return commentEntity;
    }

    public static CommentDto converterEntityWithUserToDto(CommentEntity commentEntity,UserEntity userCreated) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(commentEntity.getText());
        commentDto.setUuid(commentEntity.getUuid());
        commentDto.setDateCreate(commentEntity.getDateCreate());
        commentDto.setUsers_id(userCreated.getName());
        commentDto.setDateEdit(commentEntity.getDateEdit());
        return commentDto;
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
