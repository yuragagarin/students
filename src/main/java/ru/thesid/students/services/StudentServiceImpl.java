package ru.thesid.students.services;

import org.springframework.stereotype.Service;
import ru.thesid.students.domain.Student;
import ru.thesid.students.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student create(Student student) {
        return repo.save(student);
    }

    @Override
    public Student remove(long id) {
        var student = repo.findById(id);
        if(student.isPresent())
            repo.deleteById(id);
        return student.get();
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll().stream().limit(1000).collect(Collectors.toList());
    }
}
