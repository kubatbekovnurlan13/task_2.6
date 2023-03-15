package kg.kubatbekov.DataJPA.service;

import kg.kubatbekov.DataJPA.model.Group;
import kg.kubatbekov.DataJPA.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group findByName(String name) {
        return groupRepository.findByName(name).orElse(new Group());
    }

    public List<Group> findLessOrEqualStudentCount(int count) {
        return groupRepository.findAll()
                .stream()
                .filter(group -> group.getStudents().size() <= count & group.getStudents().size() != 0)
                .toList();
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group update(Group group) {
        Optional<Group> groupToUpdate = groupRepository.findById(group.getGroupId());
        if (groupToUpdate.isPresent()) {
            groupToUpdate.get().setGroupName(group.getGroupName());
            groupToUpdate.get().setStudents(group.getStudents());
            return groupRepository.save(groupToUpdate.get());
        }
        return new Group();
    }

    public void deleteById(int group_id) {
        groupRepository.deleteById(group_id);
    }
}
