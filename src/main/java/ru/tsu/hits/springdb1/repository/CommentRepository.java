package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity,String> {
    List<CommentEntity> findByCreatedUserComments(UserEntity userEntity);
}
