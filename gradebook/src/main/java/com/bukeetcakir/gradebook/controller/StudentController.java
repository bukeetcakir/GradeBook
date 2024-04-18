package com.bukeetcakir.gradebook.controller;

import com.bukeetcakir.gradebook.dto.examResult.AverageExamResultResponse;
import com.bukeetcakir.gradebook.dto.student.StudentResponse;
import com.bukeetcakir.gradebook.dto.student.StudentSaveRequest;
import com.bukeetcakir.gradebook.dto.student.StudentUpdateRequest;
import com.bukeetcakir.gradebook.service.ExamResultService;
import com.bukeetcakir.gradebook.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final ExamResultService examResultService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents() {
        var students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        var student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/exam-results")
    public ResponseEntity<List<AverageExamResultResponse>> getExamResultByStudentId(@PathVariable Long id) {
        var examResults = examResultService.getExamResultsWithAverageScore(id);
        return ResponseEntity.ok(examResults);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentSaveRequest request) {
        var student = studentService.saveStudent(request);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequest request) {
        var student = studentService.updateStudent(id, request);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
