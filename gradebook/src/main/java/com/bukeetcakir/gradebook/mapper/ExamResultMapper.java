package com.bukeetcakir.gradebook.mapper;

import com.bukeetcakir.gradebook.dto.examResult.ExamResultResponse;
import com.bukeetcakir.gradebook.dto.examResult.ExamResultSaveRequest;
import com.bukeetcakir.gradebook.entity.ExamResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ExamResultMapper {
    ExamResultMapper INSTANCE = Mappers.getMapper(ExamResultMapper.class);

    ExamResult toExamResult(ExamResultSaveRequest request);

    ExamResultResponse toExamResultResponse(ExamResult examResult);

    List<ExamResultResponse> toExamResultResponses(List<ExamResult> examResults);

}
