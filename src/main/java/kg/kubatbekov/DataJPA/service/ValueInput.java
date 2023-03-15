package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.model.Course;
import kg.kubatbekov.DataJPA.model.Group;
import kg.kubatbekov.DataJPA.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class ValueInput implements CommandLineRunner {
    private final Scanner in = new Scanner(System.in);
    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(ValueInput.class);

    @Autowired
    public ValueInput(GroupService groupService, CourseService courseService, StudentService studentService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        runConsoleApp();
    }

    private void runConsoleApp() {
        while (true) {
            logger.info("Log from {}, console application started", ValueInput.class.getSimpleName());

            int command = getCommand();
            if (command <= 3) {
                int modelName = getModelName();
                String value = getValue();
                if (Objects.equals(value, "stop")) {
                    in.close();
                    System.exit(0);
                }
                if (modelName == 1) {
                    studentService(command, value);
                } else if (modelName == 2) {
                    groupService(command, value);
                } else {
                    courseService(command, value);
                }
            } else {
                makeNewAddedFeatures(command);
            }
        }
    }

    private void makeNewAddedFeatures(int command) {
        if (command == 4) {
            findLessOrEqualStudentCountService();
        } else if (command == 5) {
            findAllStudentsOfCourseService();
        } else if (command == 6) {
            addNewStudentService();
        } else if (command == 7) {
            deleteStudentByStudentIdService();
        } else if (command == 8) {
            addStudentToCourseFromListService();
        } else if (command == 9) {
            removeStudentFromCourseService();
        }
    }

    private void removeStudentFromCourseService() {
        logger.info("Log from {}, removeStudentFromCourseService() method was called", ValueInput.class.getSimpleName());

        String info = """

                Remove the student from one of their courses

                Enter the id of student :""";
        System.out.print(info);
        int idOfStudent = in.nextInt();
        System.out.println("Enter the id of course :");
        int idOfCourse = in.nextInt();

        courseService.deleteStudent(idOfCourse, idOfStudent);
        runConsoleApp();
    }

    private void addStudentToCourseFromListService() {
        logger.info("Log from {}, addStudentToCourseFromListService() method was called", ValueInput.class.getSimpleName());

        String info = """

                Add a student to the course (from the list)

                Course list, pick one :""";
        System.out.print(info);
        List<Course> courses = courseService.findAll();
        for (Course course : courses) {
            System.out.println(course);
        }
        System.out.println("Student list, pick one:");
        List<Student> students = studentService.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.print("Enter the course id that you choose :");
        int courseId = in.nextInt();

        System.out.print("Enter the student id that you choose :");
        int studentId = in.nextInt();

        courseService.addStudent(courseId, studentId);
        runConsoleApp();
    }

    private void deleteStudentByStudentIdService() {
        logger.info("Log from {}, deleteStudentByStudentIdService() method was called", ValueInput.class.getSimpleName());

        String info = """

                Delete student by STUDENT_ID

                Enter the id of student :""";
        System.out.print(info);
        int idOfStudent = in.nextInt();

        studentService.deleteById(idOfStudent);
        runConsoleApp();
    }

    private void addNewStudentService() {
        logger.info("Log from {}, addNewStudentService() method was called", ValueInput.class.getSimpleName());

        String info = """

                Add a new student

                Enter the first name of student :""";
        System.out.print(info);
        in.nextLine();
        String firstName = in.nextLine();
        System.out.println("Enter the last name of student:");
        String lastName = in.nextLine();

        Student student = new Student(firstName, lastName);
        studentService.save(student);
        runConsoleApp();
    }

    private void findAllStudentsOfCourseService() {
        logger.info("Log from {}, findAllStudentsOfCourseService() method was called", ValueInput.class.getSimpleName());

        String info = """

                Find all the students related to the course with the given name

                Enter the name of course :""";
        System.out.print(info);
        in.nextLine();
        String courseName = in.nextLine();

        List<Student> students = courseService.findAllStudents(courseName);
        if (students.size() == 0) {
            System.out.println("Not found !");
        } else {
            System.out.println("Found all students of course:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
        runConsoleApp();
    }

    private void findLessOrEqualStudentCountService() {
        logger.info("Log from {}, findLessOrEqualStudentCountService() method was called", ValueInput.class.getSimpleName());

        String info = """

                Find all the groups with less or equal student count.

                Enter the count of student :""";
        System.out.print(info);
        int studentCount = in.nextInt();

        List<Group> groups = groupService.findLessOrEqualStudentCount(studentCount);
        if (groups.size() == 0) {
            System.out.println("Not found !");
        } else {
            System.out.println("Found all groups:");
            for (Group group : groups) {
                System.out.println(group);
            }
        }
        runConsoleApp();
    }

    private int getCommand() {

        String info = """

                    What would you like to do?
                0 - exit
                1 - save
                2 - delete by id
                3 - get by name

                    Or do you want to carry out these operations?
                4 - Find all the groups with less or equal student count
                5 - Find all the students related to the course with the given name
                6 - Add a new student
                7 - Delete student by STUDENT_ID
                8 - Add a student to the course (from the list)
                9 - Remove the student from one of their courses

                Enter number of command:""";
        System.out.print(info);
        int command = in.nextInt();
        if (command == 0) {
            System.exit(0);
            in.close();
        } else if (command > 9 || command < 1) {
            System.out.println("Wrong command!");
            getCommand();
        }
        return command;
    }

    private int getModelName() {
        String info = """

                    To whom do you want to perform this operation?
                0 - exit
                1 - Student
                2 - Group
                3 - Course

                Enter number of command:""";
        System.out.print(info);

        int command = in.nextInt();
        if (command == 0) {
            System.exit(0);
            in.close();
        } else if (command > 3 || command < 1) {
            System.out.println("Wrong command!");
            getModelName();
        }

        return command;
    }

    private String getValue() {
        in.nextLine();

        String info = """

                Enter 'stop' to exit.
                Enter value:""";
        System.out.print(info);
        return in.nextLine();
    }

    private void studentService(int command, String value) {
        if (command == 1) {
            logger.info("Log from {}, studentService.save(student) method was called", ValueInput.class.getSimpleName());

            Student student = new Student(value, "default desc");
            studentService.save(student);
            runConsoleApp();
        } else if (command == 2) {
            logger.info("Log from {}, studentService.deleteById(id) method was called", ValueInput.class.getSimpleName());

            studentService.deleteById(Integer.parseInt(value));
            runConsoleApp();
        } else if (command == 3) {
            logger.info("Log from {}, studentService.getByName(value) method was called", ValueInput.class.getSimpleName());

            Student student = studentService.findByName(value);
            System.out.println("Result: " + student);
            runConsoleApp();
        }
    }

    private void groupService(int command, String value) {
        if (command == 1) {
            logger.info("Log from {}, groupService.save(group) method was called", ValueInput.class.getSimpleName());

            Group group = new Group(value);
            groupService.save(group);
            runConsoleApp();
        } else if (command == 2) {
            logger.info("Log from {}, groupService.deleteById(value) method was called", ValueInput.class.getSimpleName());

            groupService.deleteById(Integer.parseInt(value));
            runConsoleApp();
        } else if (command == 3) {
            logger.info("Log from {}, groupService.getByName(value) method was called", ValueInput.class.getSimpleName());

            Group group = groupService.findByName(value);
            System.out.println("Result: " + group);
            runConsoleApp();
        }
    }

    private void courseService(int command, String value) {
        if (command == 1) {
            logger.info("Log from {}, courseService.save(course) method was called", ValueInput.class.getSimpleName());

            Course course = new Course(value, "Default Desc");
            courseService.save(course);
            runConsoleApp();
        } else if (command == 2) {
            logger.info("Log from {}, courseService.deleteById(value) method was called", ValueInput.class.getSimpleName());

            courseService.deleteById(Integer.parseInt(value));
            runConsoleApp();
        } else if (command == 3) {
            logger.info("Log from {}, courseService.getByName(value) method was called", ValueInput.class.getSimpleName());

            System.out.println("Course: " + courseService.findByName(value));
            runConsoleApp();
        }
    }
}
