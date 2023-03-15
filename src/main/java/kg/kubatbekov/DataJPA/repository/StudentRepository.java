package kg.kubatbekov.DataJPA.repository;

import kg.kubatbekov.DataJPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select s from Student s where lower(s.firstName) = lower(:name)")
    Optional<Student> findByName(@Param("name") String name);

}
