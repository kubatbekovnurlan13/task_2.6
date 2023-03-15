package kg.kubatbekov.DataJPA.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> students;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Student getStudentByIdFromList(int studentId) {
        return students.stream().filter(v -> v.getStudentId() == studentId).toList().get(0);
    }

    public Student getStudentByNameFromList(String studentName) {
        return students.stream().filter(v -> Objects.equals(v.getFirstName(), studentName)).toList().get(0);
    }

    @Override
    public String toString() {
        return "Group: " +
                "group_id=" + groupId +
                ", group_name='" + groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId && Objects.equals(groupName, group.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName);
    }
}
