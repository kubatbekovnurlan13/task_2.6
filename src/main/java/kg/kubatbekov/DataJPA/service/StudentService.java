package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.model.Student;
import kg.kubatbekov.DataJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findByName(String name) {
        return studentRepository.findByName(name).orElse(new Student());
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

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

    public void deleteById(int student_id) {
        studentRepository.deleteById(student_id);
    }

    public Student findById(int student_id) {
        return studentRepository.findById(student_id).orElse(new Student());
    }
}
