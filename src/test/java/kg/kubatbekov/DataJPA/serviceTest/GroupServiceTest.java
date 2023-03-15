package kg.kubatbekov.DataJPA.serviceTest;

import kg.kubatbekov.DataJPA.container.PostgresContainer;
import kg.kubatbekov.DataJPA.model.Group;
import kg.kubatbekov.DataJPA.service.GroupService;
import kg.kubatbekov.DataJPA.service.ValueInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GroupServiceTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private GroupService groupService;

    @Test
    void save_testSave_whenValueInput() {
        Group group = new Group("group_name");
        Group actual = groupService.save(group);
        Assertions.assertEquals(group, actual);
    }

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> groupService.save(null));
        Assertions.assertEquals("Entity must not be null", exception.getMessage());
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Group expected = new Group(1, "group_1");
        Group actual = groupService.findByName(expected.getGroupName());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Group expected = new Group();
        Group actual = groupService.findByName("wrong_value");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = groupService.findAll().size();
        Assertions.assertEquals(10, actual);
    }

    @Test
    void findLessOrEqualStudentAccounts_testFindLessOrEqualStudentAccounts_whenThereIsValues() {
        int actual = groupService.findLessOrEqualStudentCount(19).size();
        Assertions.assertEquals(3, actual);
    }

    @Test
    void findLessOrEqualStudentAccounts_testFindLessOrEqualStudentAccounts_whenThereIsNoValues() {
        int actual = groupService.findLessOrEqualStudentCount(1).size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInput() {
        Group group = new Group(1, "group_1");

        Group actual = groupService.update(group);
        Assertions.assertEquals(group, actual);
    }

    @Test
    void update_testUpdateOfValue_whenValueInputNotUpdated() {
        Group actual = groupService.update(new Group());
        Group expected = new Group();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update_testUpdateOfValue_whenNullInput() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> groupService.update(null));
        Assertions.assertEquals("Cannot invoke \"kg.kubatbekov.DataJPA.model.Group.getGroupId()\" because \"group\" is null", exception.getMessage());
    }
}
