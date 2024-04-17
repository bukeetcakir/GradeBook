package com.bukeetcakir.gradebook.mapper;

import com.bukeetcakir.gradebook.dto.student.StudentResponse;
import com.bukeetcakir.gradebook.dto.student.StudentSaveRequest;
import com.bukeetcakir.gradebook.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "number", source = "number")
    Student toStudent(StudentSaveRequest request);

    StudentResponse toStudentResponse(Student student);

    List<StudentResponse> toStudentResponses(List<Student> students);
}
