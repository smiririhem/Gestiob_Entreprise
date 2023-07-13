package com.fullstack.gestionentreprise.entity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Task {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    private String title;
    private String type;
    private Date dueDate;
    private String description;
@ManyToMany
    private List<Employee> employees;
}
