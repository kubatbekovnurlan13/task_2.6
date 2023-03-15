package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.dao.GroupDAO;
import kg.kubatbekov.DataJPA.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupDAO groupDAO;

    @Autowired
    public GroupService(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public Group save(Group group) {
        return groupDAO.save(group);
    }

    public Group findByName(String name) {
        return groupDAO.findByName(name);
    }

    public List<Group> findLessOrEqualStudentCount(int count) {
        return groupDAO.findLessOrEqualStudentCount(count);
    }

    public List<Group> findAll() {
        return groupDAO.findAll();
    }

    public Group update(Group group) {
        return groupDAO.update(group);
    }

    public void deleteById(int group_id) {
        groupDAO.deleteById(group_id);
    }
}
