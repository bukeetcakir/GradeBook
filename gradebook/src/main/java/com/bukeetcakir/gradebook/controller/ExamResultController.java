package com.bukeetcakir.gradebook.controller;

import com.bukeetcakir.gradebook.dto.examResult.ExamResultResponse;
import com.bukeetcakir.gradebook.dto.examResult.ExamResultSaveRequest;
import com.bukeetcakir.gradebook.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exam-results")
@RequiredArgsConstructor
public class ExamResultController {
    private final ExamResultService examResultService;

    @GetMapping
    public ResponseEntity<List<ExamResultResponse>> getExamResults() {
        var examResults = examResultService.getAllExamResults();
        return ResponseEntity.ok(examResults);
    }

    @PostMapping
    public ResponseEntity<ExamResultResponse> saveExamResult(@RequestBody ExamResultSaveRequest request) {
        var examResult = examResultService.saveExamResult(request);
        return ResponseEntity.ok(examResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResultResponse> updateExamResult(@PathVariable Long id, @RequestBody ExamResultSaveRequest request) {
        var examResult = examResultService.updateExamResult(id, request);
        return ResponseEntity.ok(examResult);
    }

    @DeleteMapping("/{id}")
    public void deleteExamResult(@PathVariable Long id) {
        examResultService.deleteExamResult(id);
    }
}
