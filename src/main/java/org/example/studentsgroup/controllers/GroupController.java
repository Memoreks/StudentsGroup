package org.example.studentsgroup.controllers;

import org.example.studentsgroup.dto.StudentDto;
import org.example.studentsgroup.dto.GroupDto;
import org.example.studentsgroup.enums.FinancingEnum;
import org.example.studentsgroup.enums.GenderEnum;
import org.example.studentsgroup.enums.StatusEnum;
import org.example.studentsgroup.services.StudentService;
import org.example.studentsgroup.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;
    private StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;

        //Тестовые данные
        groupService.create(new GroupDto(183, "Прикладная математика"));
        groupService.create(new GroupDto(182, "Программирование микроконтроллеров"));
        groupService.create(new GroupDto(181, "Исследование алгоритмов"));
        studentService.create(new StudentDto(1, "Яков", GenderEnum.Male, StatusEnum.Expelled,
                FinancingEnum.OwnFunds, 181));
        studentService.create(new StudentDto(2, "Алексей", GenderEnum.Male, StatusEnum.Study,
                FinancingEnum.Budget, 183));
        studentService.create(new StudentDto(3, "Иван", GenderEnum.Male, StatusEnum.Study,
                FinancingEnum.Budget, 182));
        studentService.create(new StudentDto(4, "Анна", GenderEnum.Female, StatusEnum.Study,
                FinancingEnum.OwnFunds, 181));
        studentService.create(new StudentDto(5, "Мария", GenderEnum.Female, StatusEnum.Expelled,
                FinancingEnum.Budget, 181));

    }

    @GetMapping
    public Collection<StudentDto> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentsByGroupId(@PathVariable Integer id) {
        Collection<StudentDto> students = studentService.getAllStudentsInGroup(id);
        if (students == null) {
            return new ResponseEntity<>("Group not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}