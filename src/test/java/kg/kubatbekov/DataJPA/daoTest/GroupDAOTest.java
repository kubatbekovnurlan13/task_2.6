package kg.kubatbekov.DataJPA.daoTest;

import kg.kubatbekov.DataJPA.container.PostgresContainer;
import kg.kubatbekov.DataJPA.dao.GroupDAO;
import kg.kubatbekov.DataJPA.model.Group;
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
public class GroupDAOTest extends PostgresContainer {
    @MockBean
    private ValueInput valueInput;
    @Autowired
    private GroupDAO groupDAO;

    @Test
    void save_testSave_whenValueInput() {
        Group group = new Group("group_name");
        Group actual = groupDAO.save(group);
        Assertions.assertEquals(group, actual);
    }

    @Test
    void save_testSave_whenInputNull() {
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class,
                () -> groupDAO.save(null));
        Assertions.assertEquals("Entity must not be null", exception.getMessage());
    }

    @Test
    void getByName_testGetByName_whenValueInput() {
        Optional<Group> expected = Optional.of(new Group(1, "group_1"));
        Optional<Group> actual = groupDAO.findByName(expected.get().getGroupName());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByName_testGetByName_whenWrongValueInput() {
        Optional<Group> expected = Optional.empty();
        Optional<Group> actual = groupDAO.findByName("wrong_value");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getById_testGetById_whenValueInput() {
        Optional<Group> expected = Optional.of(new Group(1, "group_1"));
        Optional<Group> actual = groupDAO.findById(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getById_testGetById_whenWrongValueInput() {
        Optional<Group> expected = Optional.empty();
        Optional<Group> actual = groupDAO.findById(100000);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = groupDAO.findAll().size();
        Assertions.assertEquals(10, actual);
    }
}
