package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import ru.tsu.hits.springdb1.entity.Priority;

@Data
public class CreateUpdateTasksDto {
    private String description;
    private String header;
    private Priority priority;
}
