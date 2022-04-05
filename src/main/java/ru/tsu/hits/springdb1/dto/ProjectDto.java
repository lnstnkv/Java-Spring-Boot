package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.springdb1.entity.TaskEntity;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private String id;
    private Date dateCreate;
    private Date dateEdit;
    private String name;
    private String description;
    private List<TaskDto> tasks;
}
