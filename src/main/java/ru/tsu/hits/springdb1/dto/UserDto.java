package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tsu.hits.springdb1.entity.Role;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private String id;
        private String name;
        private String email;
        private String password;
        private Date dateCreate;
        private Date dateEdit;
        private Role role;
        private List<TaskDto> tasks;
}
