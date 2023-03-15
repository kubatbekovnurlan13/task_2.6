package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.dao.CourseDAO;
import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Course save(Course course) {
        return courseDAO.save(course);
    }

    public List<Student> findAllStudents(String courseName) {
        return courseDAO.findAllStudents(courseName);
    }

    public Course addStudent(int course_id, int student_id) {
        return courseDAO.addStudent(course_id, student_id);
    }

    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    public Course findByName(String name) {
        return courseDAO.findByName(name);
    }

    public Course update(Course course) {
        return courseDAO.update(course);
    }

    public void deleteById(int course_id) {
        courseDAO.deleteById(course_id);
    }

    public void deleteStudent(int course_id, int student_id) {
        courseDAO.deleteStudent(course_id, student_id);
    }
}
