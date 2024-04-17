package com.bukeetcakir.gradebook.repository;

import com.bukeetcakir.gradebook.dto.examResult.ExamResultResponse;
import com.bukeetcakir.gradebook.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    int countExamResultByStudentIdAndCourseId(Long studentId, Long courseId);
}
