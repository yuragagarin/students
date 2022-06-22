package ru.thesid.students.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.thesid.students.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
