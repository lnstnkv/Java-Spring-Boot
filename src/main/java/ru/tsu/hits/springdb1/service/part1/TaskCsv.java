package ru.tsu.hits.springdb1.service.part1;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TaskCsv {
    @CsvBindByPosition(position = 0)
    private TypeTasks typeTasks;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private String author;

    @CsvBindByPosition(position = 3)
    private String performer;

    @CsvBindByPosition(position = 4)
    private String priority;

    @CsvBindByPosition(position = 5)
    private String creationDate;

}
