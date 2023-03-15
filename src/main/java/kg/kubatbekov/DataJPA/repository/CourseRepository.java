package kg.kubatbekov.DataJPA.repository;

import kg.kubatbekov.DataJPA.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "select c from Course c where lower(c.courseName) = lower(:name)")
    Optional<Course> findByName(@Param("name") String name);
}
