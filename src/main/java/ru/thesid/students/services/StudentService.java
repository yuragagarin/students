package ru.thesid.students.services;

import ru.thesid.students.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student create(Student student);
    Student remove(long id);
    List<Student> getAll();
}
