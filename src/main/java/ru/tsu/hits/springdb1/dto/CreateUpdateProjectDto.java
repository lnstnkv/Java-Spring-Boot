package ru.tsu.hits.springdb1.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CreateUpdateProjectDto {
    private Date dateCreate;
    private Date dateEdit;
    @NotBlank
    private String name;
    private String description;
}
