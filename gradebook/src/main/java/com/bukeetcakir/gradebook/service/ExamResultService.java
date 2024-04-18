package com.bukeetcakir.gradebook.service;

import com.bukeetcakir.gradebook.dto.examResult.AverageExamResultResponse;
import com.bukeetcakir.gradebook.dto.examResult.ExamResultResponse;
import com.bukeetcakir.gradebook.dto.examResult.ExamResultSaveRequest;
import com.bukeetcakir.gradebook.entity.Course;
import com.bukeetcakir.gradebook.mapper.ExamResultMapper;
import com.bukeetcakir.gradebook.repository.CourseRepository;
import com.bukeetcakir.gradebook.repository.ExamResultRepository;
import com.bukeetcakir.gradebook.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<AverageExamResultResponse> getExamResultsWithAverageScore(Long studentId) {
        List<AverageExamResultResponse> examResults = new ArrayList<>();
        var courseIds = examResultRepository.findCourseIdsByStudentId(studentId);
        for (var courseId : courseIds) {
            if (isCourseCompleted(studentId, courseId)) {
                var scores = examResultRepository.findScoresByStudentIdAndCourseId(studentId, courseId);
                var averageScore = examResultRepository.getAverageScoreByStudentIdAndCourseId(studentId, courseId);
                var course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
                examResults.add(new AverageExamResultResponse(course.getName(), scores, averageScore));
            }
        }
        return examResults;
    }

    public ExamResultResponse saveExamResult(ExamResultSaveRequest request) {
        if (isCourseCompleted(request.studentId(), request.courseId())) {
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


    private boolean isCourseCompleted(Long studentId, Long courseId) {
        var completedScoreCount = examResultRepository.countExamResultByStudentIdAndCourseId(studentId, courseId);
        return completedScoreCount == COMPLETED_SCORE_THRESHOLD;
    }
}
