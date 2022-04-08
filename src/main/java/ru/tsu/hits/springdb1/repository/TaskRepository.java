package ru.tsu.hits.springdb1.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity,String> , JpaSpecificationExecutor<TaskEntity> {

    List<TaskEntity> findByCreatedUser(UserEntity userEntity);

    List<TaskEntity> findByPerformerUser(UserEntity userEntity);

    List<TaskEntity> findByProject(ProjectEntity projectEntity);

    List<TaskEntity> findAll(Specification<TaskEntity> specification);

    List<TaskEntity> findAllByComments(String name);


}
