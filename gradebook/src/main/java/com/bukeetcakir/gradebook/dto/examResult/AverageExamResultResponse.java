package com.bukeetcakir.gradebook.dto.examResult;

import java.util.List;

public record AverageExamResultResponse(String courseName,
                                        List<Integer> scores,
                                        Double averageScore) {
}
