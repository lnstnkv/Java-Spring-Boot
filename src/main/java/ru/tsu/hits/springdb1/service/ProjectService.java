package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.UserDtoConverter;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.exception.ProjectNotFoundException;
import ru.tsu.hits.springdb1.exception.UserNotFoundException;
import ru.tsu.hits.springdb1.repository.ProjectRepository;
import ru.tsu.hits.springdb1.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectDto save(CreateUpdateProjectDto createUpdateProjectDto) {
        ProjectEntity projectEntity = ProjectDtoConverter.converterDtoToEntity(createUpdateProjectDto);
        projectEntity = projectRepository.save(projectEntity);
        return ProjectDtoConverter.converterEntityToDto(projectEntity);

    }
    @Transactional(readOnly = true)
    public ProjectEntity getProjectEntityById(String id){
        return projectRepository.findById(id)
                .orElseThrow(()->new ProjectNotFoundException("Проект с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectDtoById(String id) {
        ProjectEntity projectEntity=getProjectEntityById(id);
        return ProjectDtoConverter.converterEntityToDto(projectEntity);
    }

}
