package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @Column(name = "id")
    private String uuid;

    @Column
    private String header;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private Priority priority;

   @ManyToOne()
   @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity createdUser;
}
