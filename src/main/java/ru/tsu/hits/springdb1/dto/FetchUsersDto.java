package ru.tsu.hits.springdb1.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FetchUsersDto {

    private Map<String,String> fields;
}
