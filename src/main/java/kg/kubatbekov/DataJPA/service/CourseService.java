package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.model.Student;
import kg.kubatbekov.DataJPA.repository.CourseRepository;
import kg.kubatbekov.DataJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Student> findAllStudents(String courseName) {
        Optional<Course> course = courseRepository.findByName(courseName);
        List<Student> students = new ArrayList<>();
        if (course.isPresent()) {
            students = course.get().getStudents();
        }
        return students;
    }

    public Course addStudent(int course_id, int student_id) {
        Optional<Student> student = studentRepository.findById(student_id);
        Optional<Course> course = courseRepository.findById(course_id);
        if (student.isPresent() && course.isPresent()) {
            course.get().getStudents().add(student.get());
            return courseRepository.save(course.get());
        }
        return new Course();
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findByName(String name) {
        return courseRepository.findByName(name).orElse(new Course());
    }

    public Course update(Course course) {
        Optional<Course> courseToUpdate = courseRepository.findById(course.getCourseId());
        if (courseToUpdate.isPresent()) {
            courseToUpdate.get().setCourseName(course.getCourseName());
            courseToUpdate.get().setCourseDescription(course.getCourseDescription());
            courseToUpdate.get().setStudents(course.getStudents());
            return courseRepository.save(courseToUpdate.get());
        }
        return new Course();
    }

    public void deleteById(int course_id) {
        courseRepository.deleteById(course_id);
    }

    public void deleteStudent(int course_id, int student_id) {
        Optional<Course> course = courseRepository.findById(course_id);
        if (course.isPresent()) {
            Student student = course.get().getStudentByIdFromList(student_id);
            course.get().getStudents().remove(student);
            courseRepository.save(course.get());
        }
    }
}
