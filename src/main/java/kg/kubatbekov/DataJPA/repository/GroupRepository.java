package kg.kubatbekov.DataJPA.repository;

import kg.kubatbekov.DataJPA.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query(value = "select g from Group g where lower(g.groupName) = lower(:name)")
    Optional<Group> findByName(@Param("name") String name);
}
