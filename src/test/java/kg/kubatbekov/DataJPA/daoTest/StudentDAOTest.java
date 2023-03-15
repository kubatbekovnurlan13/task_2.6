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
        Student student = new Student("first_2", "last_2");

        Student actual = studentDAO.findByName(student.getFirstName());
        actual.setStudentId(0);
        assertEquals(student, actual);
    }


    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Student student = new Student();
        Student actual = studentDAO.findByName("wrong_value");
        assertEquals(student, actual);
    }


    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Student student = new Student(1,"first_name_new", "last_name_new");

        Student actual = studentDAO.update(student);
        assertEquals(student, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        Student actual = studentDAO.update(new Student());
        Student expected = new Student();
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> studentDAO.update(null));
        assertEquals("Cannot invoke \"kg.kubatbekov.DataJPA.model.Student.getStudentId()\" because \"student\" is null", exception.getMessage());
    }
}
