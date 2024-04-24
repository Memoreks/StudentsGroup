package org.example.studentsgroup.controllers;

import org.example.studentsgroup.dto.StudentDto;
import org.example.studentsgroup.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        StudentDto studentDto = studentService.getById(id);

        if (studentDto == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
        }
    }

    @GetMapping
    public Collection<StudentDto> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentDto studentDto) {
        studentDto = studentService.create(studentDto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto) {
        if (studentDto == null) {
            return new ResponseEntity<>("Invalid ID supplied", HttpStatus.BAD_REQUEST);
        }
        studentDto = studentService.update(studentDto);
        if (studentDto == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successful operation", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        StudentDto studentDto = studentService.deleteById(id);

        if (studentDto == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}