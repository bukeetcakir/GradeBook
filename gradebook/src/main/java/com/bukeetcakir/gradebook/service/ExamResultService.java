package com.bukeetcakir.gradebook.service;

import com.bukeetcakir.gradebook.dto.examResult.ExamResultResponse;
import com.bukeetcakir.gradebook.dto.examResult.ExamResultSaveRequest;
import com.bukeetcakir.gradebook.mapper.ExamResultMapper;
import com.bukeetcakir.gradebook.repository.CourseRepository;
import com.bukeetcakir.gradebook.repository.ExamResultRepository;
import com.bukeetcakir.gradebook.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private static final int COMPLETED_SCORE_THRESHOLD = 3;

    public List<ExamResultResponse> getAllExamResults() {
        var examResults = examResultRepository.findAll();
        return ExamResultMapper.INSTANCE.toExamResultResponses(examResults);
    }

    //    public ExamResultResponse getExamResultById(Long examResultId) {
//        var course = examResultRepository.findById(examResultId)
//                .orElseThrow(() -> new RuntimeException("ExamResult not found"));
//        return ExamResultMapper.INSTANCE.toExamResultResponse(course);
//    }
    public ExamResultResponse saveExamResult(ExamResultSaveRequest request) {
        if (isCourseCompleted(request)) {
            throw new RuntimeException("Course already completed");
        }
        var course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        var student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        var examResult = ExamResultMapper.INSTANCE.toExamResult(request);
        examResultRepository.save(examResult);
        return ExamResultMapper.INSTANCE.toExamResultResponse(examResult);
    }

    public ExamResultResponse updateExamResult(Long examResultId, ExamResultSaveRequest request) {
        var examResult = examResultRepository.findById(examResultId)
                .orElseThrow(() -> new RuntimeException("ExamResult not found"));
        examResult.setScore(request.score());
        examResultRepository.save(examResult);
        return ExamResultMapper.INSTANCE.toExamResultResponse(examResult);
    }

    public void deleteExamResult(Long examResultId) {
        var examResult = examResultRepository.findById(examResultId)
                .orElseThrow(() -> new RuntimeException("ExamResult not found"));
        examResultRepository.deleteById(examResult.getId());
    }


    private boolean isCourseCompleted(ExamResultSaveRequest request) {
        var completedScoreCount = examResultRepository.countExamResultByStudentIdAndCourseId(request.studentId(), request.courseId());
        return completedScoreCount == COMPLETED_SCORE_THRESHOLD;
    }
}
