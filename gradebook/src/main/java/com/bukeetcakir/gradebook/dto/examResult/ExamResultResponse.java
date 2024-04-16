package com.bukeetcakir.gradebook.dto.examResult;

public record ExamResultResponse(Long id,
                                 Long studentId,
                                 Long courseId,
                                 int score) {
}
