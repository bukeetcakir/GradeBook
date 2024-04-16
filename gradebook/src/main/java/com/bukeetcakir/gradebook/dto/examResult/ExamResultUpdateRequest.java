package com.bukeetcakir.gradebook.dto.examResult;

public record ExamResultUpdateRequest(Long id,
                                      Long studentId,
                                      Long courseId,
                                      int score) {
}
