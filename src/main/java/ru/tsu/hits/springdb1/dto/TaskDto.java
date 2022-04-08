package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.springdb1.entity.Priority;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String id;
    private String description;
    @NotBlank
    private String header;
    private Priority priority;
    private String creator_id;
    private String performer_id;
    private String project_id;
    private Date dateCreate;
    private Date dateEdit;
    private Integer timeEstimate;
    private List<CommentDto> comments;
}
