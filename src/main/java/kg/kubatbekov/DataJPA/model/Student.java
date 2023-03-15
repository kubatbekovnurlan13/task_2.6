package kg.kubatbekov.DataJPA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable = false)
    private Group group;

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private List<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(int studentId, String firstName, String lastName, Group group) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    public Student(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public Course getCourseByIdFromList(int courseId){
        return courses.stream().filter(v->v.getCourseId()==courseId).toList().get(0);
    }

    public Course getCourseByNameFromList(String courseName){
        return courses.stream().filter(v-> Objects.equals(v.getCourseName(), courseName)).toList().get(0);
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + studentId +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName);
    }
}
