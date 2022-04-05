package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

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
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
}
