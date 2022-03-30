package ru.tsu.hits.springdb1.dto;


import lombok.Data;
import ru.tsu.hits.springdb1.entity.Role;

import java.util.Date;

@Data
public class CreateUserDto {

    private String name;
    private String email;
    private String password;
    private Date dateCreate;
    private Date dateEdit;
    private Role role;

}
