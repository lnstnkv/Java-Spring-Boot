package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.springdb1.entity.Priority;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String id;
    private String description;
    private String header;
    private Priority priority;
    private String users_id;
    private String project_id;
    private Date dateCreate;
    private Date dateEdit;
    private Integer timeEstimate;
}
