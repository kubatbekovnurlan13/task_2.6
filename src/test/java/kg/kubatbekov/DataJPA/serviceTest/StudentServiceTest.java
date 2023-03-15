package kg.kubatbekov.DataJPA.serviceTest;

import kg.kubatbekov.DataJPA.container.PostgresContainer;
import kg.kubatbekov.DataJPA.model.Student;
import kg.kubatbekov.DataJPA.service.StudentService;
import kg.kubatbekov.DataJPA.service.ValueInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StudentServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private StudentService studentService;

    @Test
    void save_testSave_whenValueInput() {
        Student student = new Student("new_student","new_desc");
        Student actual = studentService.save(student);
        assertEquals(student,actual);
    }

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> studentService.save(null));
        assertEquals("Entity must not be null", exception.getMessage());
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = studentService.findAll().size();
        assertEquals(110, actual);
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Student student = new Student("first_2", "last_2");

        Student actual = studentService.findByName(student.getFirstName());
        actual.setStudentId(0);
        assertEquals(student, actual);
    }


    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Student student = new Student();
        Student actual = studentService.findByName("wrong_value");
        assertEquals(student, actual);
    }


    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Student student = new Student(1,"first_name_new", "last_name_new");

        Student actual = studentService.update(student);
        assertEquals(student, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        Student actual = studentService.update(new Student());
        Student expected = new Student();
        assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> studentService.update(null));
        assertEquals("Cannot invoke \"kg.kubatbekov.DataJPA.model.Student.getStudentId()\" because \"student\" is null", exception.getMessage());
    }
}
