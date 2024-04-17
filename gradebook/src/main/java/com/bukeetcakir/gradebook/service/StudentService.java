package com.bukeetcakir.gradebook.service;

import com.bukeetcakir.gradebook.dto.student.StudentResponse;
import com.bukeetcakir.gradebook.dto.student.StudentSaveRequest;
import com.bukeetcakir.gradebook.dto.student.StudentUpdateRequest;
import com.bukeetcakir.gradebook.mapper.StudentMapper;
import com.bukeetcakir.gradebook.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentResponse> getAllStudents() {
        var students = studentRepository.findAll();
        return StudentMapper.INSTANCE.toStudentResponses(students);
    }

    public StudentResponse getStudentById(Long studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return StudentMapper.INSTANCE.toStudentResponse(student);
    }

    public StudentResponse saveStudent(StudentSaveRequest request) {
        if (isStudentAlreadyExist(request)) {
            throw new RuntimeException("Student already exist");
        }
        var student = StudentMapper.INSTANCE.toStudent(request);
        studentRepository.save(student);
        return StudentMapper.INSTANCE.toStudentResponse(student);
    }

    public StudentResponse updateStudent(Long studentId, StudentUpdateRequest request) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFullName(request.fullName());
        student.setEmail(request.email());
        student.setGsmNumber(request.gsmNumber());
        studentRepository.save(student);
        return StudentMapper.INSTANCE.toStudentResponse(student);
    }

    public void deleteStudent(Long studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.deleteById(student.getId());
    }

    private boolean isStudentAlreadyExist(StudentSaveRequest request) {
        var student = studentRepository.findStudentByNumber(request.number().toString());
        if (student != null) {
            return true;
        } else {
            return false;
        }
    }

}
