package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private String id;
    private Date dateCreate;
    private Date dateEdit;
    private String name;
    private String description;
}
