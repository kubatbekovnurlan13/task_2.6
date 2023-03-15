package kg.kubatbekov.DataJPA.daoTest;

import kg.kubatbekov.DataJPA.container.PostgresContainer;
import kg.kubatbekov.DataJPA.dao.CourseDAO;
import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CourseDAOTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private CourseDAO courseDAO;

    @Test
    void save_testSave_whenValueInput() {
        Course course = new Course("new_course", "new_course_description");
        Course actual = courseDAO.save(course);
        Assertions.assertEquals(course, actual);
    }

    @Test
    void save_testSave_whenNullInput() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> courseDAO.save(null));
        Assertions.assertEquals("Entity must not be null", exception.getMessage());
    }

    @Test
    void getAll_testGetAllCourses_whenThereIsCourses() {
        int actual = courseDAO.findAll().size();
        Assertions.assertEquals(11, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenCourseNameInput() {
        Optional<Course> course = Optional.of(new Course(7, "course_7", "course_description_7"));

        Optional<Course> actual = courseDAO.findByName(course.get().getCourseName());
        Assertions.assertEquals(course, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenWrongCourseNameInput() {
        Optional<Course> excepted = Optional.empty();
        Optional<Course> actual = courseDAO.findByName("wrong_name");
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void getById_testGetCourseById_whenValueInput() {
        Optional<Course> excepted = Optional.of(new Course(1,"course_1","course_description_1"));
        Optional<Course> actual = courseDAO.findById(1);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void getById_testGetCourseById_whenWrongValueInput() {
        Optional<Course> excepted = Optional.empty();
        Optional<Course> actual = courseDAO.findById(1000000);
        Assertions.assertEquals(excepted, actual);
    }


}
