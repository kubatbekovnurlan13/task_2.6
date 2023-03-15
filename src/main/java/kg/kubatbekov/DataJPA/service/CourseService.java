package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.dao.CourseDAO;
import kg.kubatbekov.DataJPA.dao.StudentDAO;
import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseDAO courseDAO;

    private final StudentDAO studentDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO, StudentDAO studentDAO) {
        this.courseDAO = courseDAO;
        this.studentDAO = studentDAO;
    }

    public Course save(Course course) {
        return courseDAO.save(course);
    }

    public List<Student> findAllStudents(String courseName) {
        Optional<Course> course = courseDAO.findByName(courseName);
        List<Student> students = new ArrayList<>();
        if (course.isPresent()) {
            students = course.get().getStudents();
        }
        return students;
    }

    public Course addStudent(int course_id, int student_id) {
        Optional<Student> student = studentDAO.findById(student_id);
        Optional<Course> course = courseDAO.findById(course_id);
        if (student.isPresent() && course.isPresent()) {
            course.get().getStudents().add(student.get());
            return courseDAO.save(course.get());
        }
        return new Course();
    }

    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    public Course findByName(String name) {
        return courseDAO.findByName(name).orElse(new Course());
    }

    public Course update(Course course) {
        Optional<Course> courseToUpdate = courseDAO.findById(course.getCourseId());
        if (courseToUpdate.isPresent()) {
            courseToUpdate.get().setCourseName(course.getCourseName());
            courseToUpdate.get().setCourseDescription(course.getCourseDescription());
            courseToUpdate.get().setStudents(course.getStudents());
            return courseDAO.save(courseToUpdate.get());
        }
        return new Course();
    }

    public void deleteById(int course_id) {
        courseDAO.deleteById(course_id);
    }

    public void deleteStudent(int course_id, int student_id) {
        Optional<Course> course = courseDAO.findById(course_id);
        if (course.isPresent()) {
            Student student = course.get().getStudentByIdFromList(student_id);
            course.get().getStudents().remove(student);
            courseDAO.save(course.get());
        }
    }
}
