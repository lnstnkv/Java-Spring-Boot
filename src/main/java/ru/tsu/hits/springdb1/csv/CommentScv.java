package ru.tsu.hits.springdb1.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tsu.hits.springdb1.entity.Role;

import java.util.List;

@ToString
@Getter
@Setter
public class CommentScv {
    @CsvBindByPosition(position = 0)
    private String creationDate;

    @CsvBindByPosition(position = 1)
    private String editDate;

    @CsvBindByPosition(position = 2)
    private String author;

    @CsvBindByPosition(position = 3)
    private String tasks;

    @CsvBindByPosition(position = 4)
    private String text;

}
