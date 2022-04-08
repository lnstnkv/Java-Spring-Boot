package ru.tsu.hits.springdb1.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tsu.hits.springdb1.entity.Role;

@ToString
@Getter
@Setter
public class ProjectCsv {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String creationDate;

    @CsvBindByPosition(position = 2)
    private String editDate;

    @CsvBindByPosition(position = 3)
    private String name;

    @CsvBindByPosition(position = 4)
    private String description;


}
