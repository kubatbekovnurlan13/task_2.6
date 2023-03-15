package kg.kubatbekov.DataJPA.daoTest;

import kg.kubatbekov.DataJPA.container.PostgresContainer;
import kg.kubatbekov.DataJPA.dao.StudentDAO;
import kg.kubatbekov.DataJPA.model.Student;
import kg.kubatbekov.DataJPA.service.ValueInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StudentDAOTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private StudentDAO studentDAO;

    @Test
    void save_testSave_whenValueInput() {
        Student student = new Student("new_student","new_desc");
        Student actual = studentDAO.save(student);
        assertEquals(student,actual);
    }

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> studentDAO.save(null));
        assertEquals("Entity must not be null", exception.getMessage());
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = studentDAO.findAll().size();
        assertEquals(110, actual);
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Optional<Student> student = Optional.of(new Student("first_2", "last_2"));

        Optional<Student> actual = studentDAO.findByName(student.get().getFirstName());
        actual.get().setStudentId(0);
        assertEquals(student, actual);
    }


    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Optional<Student> student = Optional.empty();
        Optional<Student> actual = studentDAO.findByName("wrong_value");
        assertEquals(student, actual);
    }

    @Test
    void getById_testGetById_whenValueInput() {
        Optional<Student> student = Optional.of(new Student(101,"first_101", "last_101"));
        Optional<Student> actual = studentDAO.findById(101);
        assertEquals(student, actual);
    }

    @Test
    void getById_testGetById_whenWrongValueInput() {
        Optional<Student> student = Optional.empty();
        Optional<Student> actual = studentDAO.findById(1000000);
        assertEquals(student, actual);
    }
}
