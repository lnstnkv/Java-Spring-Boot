package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private String id;
        private String name;
        private String email;
        private String password;
        private List<TaskDto> tasks;
}
