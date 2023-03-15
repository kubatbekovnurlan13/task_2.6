package kg.kubatbekov.DataJPA.daoInterface;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T t);

    Optional<T> findByName(String name);

    List<T> findAll();

    Optional<T> findById(int id);

    void deleteById(int id);
}
