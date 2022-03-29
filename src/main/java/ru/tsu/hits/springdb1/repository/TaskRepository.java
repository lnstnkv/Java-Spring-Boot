package ru.tsu.hits.springdb1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.TaskEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity,String> {

    List<TaskEntity> findByUser(UserEntity userEntity);

}
