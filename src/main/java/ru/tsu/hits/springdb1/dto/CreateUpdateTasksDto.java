package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import ru.tsu.hits.springdb1.entity.Priority;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.entity.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Data
public class CreateUpdateTasksDto {
    private String description;
    private String header;
    private Priority priority;
    private String users_id;
    private String project_id;
    private Date dateCreate;
    private Date dateEdit;
    private Integer timeEstimate;
}
