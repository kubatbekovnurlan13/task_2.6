package kg.kubatbekov.DataJPA.dao;

import kg.kubatbekov.DataJPA.daoInterface.DAO;
import kg.kubatbekov.DataJPA.model.Student;
import kg.kubatbekov.DataJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO implements DAO<Student> {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentDAO(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findByName(String name) {
        return studentRepository.findByName(name).orElse(new Student());
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @Override
    public Student update(Student student) {
        Optional<Student> studentToUpdate = studentRepository.findById(student.getStudentId());
        if (studentToUpdate.isPresent()) {
            studentToUpdate.get().setFirstName(student.getFirstName());
            studentToUpdate.get().setLastName(student.getLastName());
            studentToUpdate.get().setGroup(student.getGroup());
            studentToUpdate.get().setCourses(student.getCourses());
            return studentRepository.save(studentToUpdate.get());
        }
        return new Student();
    }

    @Override
    public void deleteById(int student_id) {
        studentRepository.deleteById(student_id);
    }
}
