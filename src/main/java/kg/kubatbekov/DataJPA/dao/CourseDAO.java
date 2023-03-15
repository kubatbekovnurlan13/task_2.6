package kg.kubatbekov.DataJPA.dao;

import kg.kubatbekov.DataJPA.daoInterface.DAO;
import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAO implements DAO<Course> {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseDAO(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(int course_id) {
        courseRepository.deleteById(course_id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    public Optional<Course> findById(int course_id) {
        return courseRepository.findById(course_id);
    }
}
