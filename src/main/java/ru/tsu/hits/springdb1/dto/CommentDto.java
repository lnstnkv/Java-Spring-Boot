package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String uuid;
    private Date dateCreate;
    private Date dateEdit;
    private String text;
    private String users_id;
}
