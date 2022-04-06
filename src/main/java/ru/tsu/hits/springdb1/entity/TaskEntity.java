package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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

    @Column
    private Integer timeEstimate;

    @Enumerated(EnumType.STRING)
    @Column
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id", referencedColumnName = "id")
    private ProjectEntity project;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="creator_id", referencedColumnName = "id")
    private UserEntity createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="performer_id", referencedColumnName = "id")
    private UserEntity performerUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_create")
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_edit")
    private Date dateEdit;

    /*@ManyToMany(mappedBy = "comment_tasks")
    private List<CommentEntity> comments;


     */
}
