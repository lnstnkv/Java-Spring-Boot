package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @Column(name = "id")
    private String uuid;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_create")
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_edit")
    private Date dateEdit;

    @Column
    private String text;

    @ManyToOne()
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private UserEntity createdUserComments;
/*
    @ManyToMany
    @JoinTable(name = "comment_tasks",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<TaskEntity> taskEntityList;


 */
}
