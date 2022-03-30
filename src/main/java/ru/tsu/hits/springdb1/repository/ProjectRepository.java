package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.ProjectEntity;

public interface ProjectRepository extends CrudRepository<ProjectEntity,String> {
}
