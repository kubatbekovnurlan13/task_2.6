package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.dao.StudentDAO;
import kg.kubatbekov.DataJPA.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return studentDAO.findByName(name);
    }

    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    public Student update(Student student) {
        return studentDAO.update(student);
    }
    public void deleteById(int student_id) {
        studentDAO.deleteById(student_id);
    }
    public Student findById(int student_id){
        return studentDAO.findById(student_id);
    }
}
