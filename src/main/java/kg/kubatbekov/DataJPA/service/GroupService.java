package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.dao.GroupDAO;
import kg.kubatbekov.DataJPA.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return groupDAO.findByName(name).orElse(new Group());
    }

    public List<Group> findLessOrEqualStudentCount(int count) {
        return groupDAO.findAll()
                .stream()
                .filter(group -> group.getStudents().size() <= count & group.getStudents().size() != 0)
                .toList();
    }

    public List<Group> findAll() {
        return groupDAO.findAll();
    }

    public Group update(Group group) {
        Optional<Group> groupToUpdate = groupDAO.findById(group.getGroupId());
        if (groupToUpdate.isPresent()) {
            groupToUpdate.get().setGroupName(group.getGroupName());
            groupToUpdate.get().setStudents(group.getStudents());
            return groupDAO.save(groupToUpdate.get());
        }
        return new Group();
    }

    public void deleteById(int group_id) {
        groupDAO.deleteById(group_id);
    }
}
