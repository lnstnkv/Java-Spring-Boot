package ru.tsu.hits.springdb1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateUpdateProjectDto {
    private Date dateCreate;
    private Date dateEdit;
    private String name;
    private String description;
}
