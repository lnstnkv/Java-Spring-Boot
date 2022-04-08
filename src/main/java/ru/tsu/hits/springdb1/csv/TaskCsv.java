package ru.tsu.hits.springdb1.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tsu.hits.springdb1.entity.Priority;

import java.util.Date;

@ToString
@Getter
@Setter
public class TaskCsv {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String creationDate;

    @CsvBindByPosition(position = 2)
    private String editDate;

    @CsvBindByPosition(position = 3)
    private String header;

    @CsvBindByPosition(position = 4)
    private String text;

    @CsvBindByPosition(position = 5)
    private String author;

    @CsvBindByPosition(position = 6)
    private String performer;

    @CsvBindByPosition(position = 7)
    private Priority priority;


    @CsvBindByPosition(position = 8)
    private String project_id;


    @CsvBindByPosition(position = 9)
    private Integer grade;


}
