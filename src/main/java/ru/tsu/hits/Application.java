package ru.tsu.hits;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        if(args.length<1){
            throw new IllegalArgumentException("Вы не ввели поисковую строку");
        }

        var searchString=args[0];
        var csvStream=Application.class.getResourceAsStream("/tasks.csv");
        var tasks= new CsvToBeanBuilder<TaskCsv>(new InputStreamReader(Objects.requireNonNull(csvStream)))
                .withSeparator(',')
                .withType(TaskCsv.class)
                .withSkipLines(1)
                .build()
                .parse();
         ArrayList<TaskCsv> tasksList = tasks.stream()
                //.filter(taskCsv -> taskCsv.getName().contains(searchString))
                .sorted(Comparator.comparing(taskCsv->taskCsv.getCreationDate().contains(searchString)))
                 .collect(ArrayList::new, // создаем ArrayList
                         ArrayList::add, // добавляем в список элемент
                         ArrayList::addAll);
                //.forEach(System.out::println);
        System.out.println(tasksList);
        tasksList.forEach(s -> System.out.println(s));
        try(FileWriter writer = new FileWriter("tasks.txt", false))
        {
            for(TaskCsv task : tasksList){
                writer.write("Тип задачи: " + task.getTypeTasks()+'\n');
                writer.append("Название задачи: " + task.getName()+'\n');
                writer.append("Автор: " + task.getAuthor()+'\n');
                writer.append("Исполнитель " + task.getPerformer()+'\n');
                writer.append("Приоритет: " + task.getPriority()+'\n');
                writer.append("Дата создания: " + task.getCreationDate()+'\n');
                writer.append('\n');
                writer.append('\n');
            }





            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
