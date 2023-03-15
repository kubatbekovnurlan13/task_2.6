package kg.kubatbekov.DataJPA.dao;

import kg.kubatbekov.DataJPA.daoInterface.DAO;
import kg.kubatbekov.DataJPA.model.Group;
import kg.kubatbekov.DataJPA.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupDAO implements DAO<Group> {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupDAO(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name).orElse(new Group());
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public List<Group> findLessOrEqualStudentCount(int count) {
        return groupRepository.findAll()
                .stream()
                .filter(group -> group.getStudents().size() <= count & group.getStudents().size() != 0)
                .toList();
    }

    @Override
    public Group update(Group group) {
        Optional<Group> groupToUpdate = groupRepository.findById(group.getGroupId());
        if (groupToUpdate.isPresent()) {
            groupToUpdate.get().setGroupName(group.getGroupName());
            groupToUpdate.get().setStudents(group.getStudents());
            return groupRepository.save(groupToUpdate.get());
        }
        return new Group();
    }

    @Override
    public void deleteById(int group_id) {
        groupRepository.deleteById(group_id);
    }
}
