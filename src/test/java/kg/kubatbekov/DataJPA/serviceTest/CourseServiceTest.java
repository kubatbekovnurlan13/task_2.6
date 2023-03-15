package kg.kubatbekov.DataJPA.serviceTest;

import kg.kubatbekov.DataJPA.container.PostgresContainer;
import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.model.Student;
import kg.kubatbekov.DataJPA.service.CourseService;
import kg.kubatbekov.DataJPA.service.StudentService;
import kg.kubatbekov.DataJPA.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CourseServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Test
    void save_testSave_whenValueInput() {
        Course course = new Course("new_course", "new_course_description");
        Course actual = courseService.save(course);
        Assertions.assertEquals(course, actual);
    }

    @Test
    void save_testSave_whenNullInput() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> courseService.save(null));
        Assertions.assertEquals("Entity must not be null", exception.getMessage());
    }

    @Test
    void addStudent_testAddStudentToCourse_whenValueInput() {
        Student student = studentService.findById(20);
        Course course = courseService.addStudent(1, 20);

        Student actual = course.getStudentByIdFromList(student.getStudentId());
        Assertions.assertEquals(student, actual);
    }

    @Test
    void addStudent_testAddStudentToCourse_whenCourseWrongValueInput() {
        Course actual = courseService.addStudent(10000, 20);
        Course expected = new Course();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addStudent_testAddStudentToCourse_whenStudentWrongValueInput() {
        Course actual = courseService.addStudent(1, 100000);
        Course expected = new Course();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllStudents_testGetAllStudentsOfCourse_whenThereIsCourseName() {
        int actual = courseService.findAllStudents("course_1").size();
        Assertions.assertEquals(4, actual);
    }

    @Test
    void getAllStudents_testGetAllStudentsOfCourse_whenThereIsWrongCourseName() {
        int actual = courseService.findAllStudents("course_10000").size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void getAll_testGetAllCourses_whenThereIsCourses() {
        int actual = courseService.findAll().size();
        Assertions.assertEquals(11, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenCourseNameInput() {
        Course course = new Course(7, "course_7", "course_description_7");

        Course actual = courseService.findByName(course.getCourseName());
        Assertions.assertEquals(course, actual);
    }

    @Test
    void getByName_testGetCourseByName_whenWrongCourseNameInput() {
        Course excepted = new Course();
        Course actual = courseService.findByName("wrong_name");
        Assertions.assertEquals(excepted, actual);
    }


    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Course course = new Course(1, "course_1_new", "course_description_1_new");
        Course actual = courseService.update(course);
        Assertions.assertEquals(course, actual);
    }

    @Test
    void update_testUpdateOfValue_whenWrongValueInput() {
        Course actual = courseService.update(new Course());
        Course expected = new Course();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> courseService.update(null));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.DataJPA.model.Course.getCourseId()\" because \"course\" is null", exception.getMessage());
    }
}
