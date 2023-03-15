package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.dao.StudentDAO;
import kg.kubatbekov.DataJPA.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student save(Student student) {
        return studentDAO.save(student);
    }

    public Student findByName(String name) {
        return studentDAO.findByName(name).orElse(new Student());
    }

    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    public Student update(Student student) {
        Optional<Student> studentToUpdate = studentDAO.findById(student.getStudentId());
        if (studentToUpdate.isPresent()) {
            studentToUpdate.get().setFirstName(student.getFirstName());
            studentToUpdate.get().setLastName(student.getLastName());
            studentToUpdate.get().setGroup(student.getGroup());
            studentToUpdate.get().setCourses(student.getCourses());
            return studentDAO.save(studentToUpdate.get());
        }
        return new Student();
    }

    public void deleteById(int student_id) {
        studentDAO.deleteById(student_id);
    }

    public Student findById(int student_id) {
        return studentDAO.findById(student_id).orElse(new Student());
    }
}
