package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity,String> {
}
