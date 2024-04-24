package org.example.studentsgroup.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.studentsgroup.dto.GroupDto;
import org.example.studentsgroup.entities.Group;
import org.example.studentsgroup.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService implements CRUDService<GroupDto> {

    private final GroupRepository repository;

    @Override
    public GroupDto getById(Integer id) {
        log.info("Get group by ID: " + id);
        Group group = repository.findById(id).orElse(null);
        if(group == null) {
            return null;
        }
        return mapToDto(group);
    }

    @Override
    public Collection<GroupDto> getAll() {
        log.info("Get all groups");
        return repository.findAll().stream()
                .map(group -> mapToDto(group))
                .toList();
    }

    @Override
    public GroupDto create(GroupDto groupDto) {
        log.info("Create group");
        Group group = mapToEntity(groupDto);
        repository.save(group);
        return mapToDto(group);
    }


    public GroupDto mapToDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        return groupDto;
    }

    public Group mapToEntity(GroupDto groupDto) {
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        return group;
    }
}