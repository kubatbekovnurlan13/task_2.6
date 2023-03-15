package kg.kubatbekov.DataJPA.daoInterface;

import java.util.List;

public interface DAO<T> {
    T save(T t);

    T findByName(String name);

    List<T> findAll();

    T update(T t);

    void deleteById(int id);
}
