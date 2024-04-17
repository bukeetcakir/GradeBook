package com.bukeetcakir.gradebook.mapper;

import com.bukeetcakir.gradebook.dto.course.CourseResponse;
import com.bukeetcakir.gradebook.dto.course.CourseSaveRequest;
import com.bukeetcakir.gradebook.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse (CourseSaveRequest request);
    CourseResponse toCourseResponse (Course course);
    List<CourseResponse> toCourseResponses (List<Course> courses);

}
