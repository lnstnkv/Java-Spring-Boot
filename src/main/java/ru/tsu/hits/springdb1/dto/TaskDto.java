package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.springdb1.entity.Priority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String id;
    private String description;
    private String header;
    private Priority priority;
    private String user_id;

}
