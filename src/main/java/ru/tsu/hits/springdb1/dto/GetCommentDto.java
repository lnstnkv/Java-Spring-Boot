package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentDto {
    private String uuid;
    private Date dateCreate;
    private Date dateEdit;
    @NotBlank
    @Length(min=10)
    private String text;
    private String users_id;
  //  private List<TaskDto> tasks;
}
