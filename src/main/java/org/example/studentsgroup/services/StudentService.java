package org.example.studentsgroup.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.studentsgroup.dto.StudentDto;
import org.example.studentsgroup.entities.Group;
import org.example.studentsgroup.entities.Student;
import org.example.studentsgroup.repositories.GroupRepository;
import org.example.studentsgroup.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService implements CRUDService<StudentDto>{

    private final StudentRepository repository;
    private final GroupRepository groupRepository;

    public StudentDto getById(Integer id) {
        log.info("Get student by ID: " + id);
        Student student = repository.findById(id).orElse(null);
        if(student == null) {
            return null;
        }
        return mapToDto(student);
    }

    public Collection<StudentDto> getAll() {
        log.info("Get all students");
        return repository.findAll().stream()
                .map(student -> mapToDto(student))
                .sorted(Comparator.comparing(StudentDto::getFullName))
                .toList();
    }

    public Collection<StudentDto> getAllStudentsInGroup(Integer id) {
        log.info("Get all students in group id " + id);
        Group group = groupRepository.findById(id).orElse(null);
        if(group == null) {
            return null;
        }
        return repository.findAll().stream()
                .filter(student -> student.getGroup().getId().equals(id))
                .map(this::mapToDto)
                .sorted(Comparator.comparing(StudentDto::getFullName))
                .toList();
    }

    public StudentDto create(StudentDto studentDto) {
        log.info("Create student");
        Student student = mapToEntity(studentDto);
        repository.save(student);
        return mapToDto(student);
    }

    public StudentDto update(StudentDto studentDto) {
        log.info("Update student");
        Student student = mapToEntity(studentDto);
        repository.save(student);
        return mapToDto(student);
    }

    public StudentDto deleteById(Integer id) {
        log.info("Delete student by id " + id);
        Student student = repository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        repository.deleteById(id);
        return mapToDto(student);
    }

    public StudentDto mapToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFullName(student.getFullName());
        studentDto.setGender(student.getGender());
        studentDto.setStatus(student.getStatus());
        studentDto.setTypeOfFinancing(student.getTypeOfFinancing());
        studentDto.setGroupId(student.getGroup().getId());
        return studentDto;
    }

    public Student mapToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFullName(studentDto.getFullName());
        student.setGender(studentDto.getGender());
        student.setStatus(studentDto.getStatus());
        student.setTypeOfFinancing(studentDto.getTypeOfFinancing());
        student.setGroup(groupRepository.findById(studentDto.getGroupId()).orElseThrow());
        return student;
    }
}