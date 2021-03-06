package ru.tsu.hits.springdb1.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class CreateUpdateComment {
    private Date dateCreate;
    private Date dateEdit;
    @NotBlank
    @Length(min=10)
    private String text;
    private String users_id;
    private List<String> tasks_id;
}
