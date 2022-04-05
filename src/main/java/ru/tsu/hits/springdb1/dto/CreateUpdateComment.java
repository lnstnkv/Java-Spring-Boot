package ru.tsu.hits.springdb1.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class CreateUpdateComment {
    private Date dateCreate;
    private Date dateEdit;
    @NotBlank
    @Min(10)
    private String text;
    private String users_id;
}
