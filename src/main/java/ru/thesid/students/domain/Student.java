package ru.thesid.students.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    String surname;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    String name;
    @Column(columnDefinition = "varchar(50)")
    String patronymic;
    LocalDate birthday;
    @Column(columnDefinition = "varchar(4)")
    String groupCode;
}
