package com.bukeetcakir.gradebook.dto.examResult;

public record ExamResultSaveRequest(Long studentId,
                                    Long courseId,
                                    int score) {
}
