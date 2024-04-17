package com.bukeetcakir.gradebook.service;

import com.bukeetcakir.gradebook.dto.course.CourseResponse;
import com.bukeetcakir.gradebook.dto.course.CourseSaveRequest;
import com.bukeetcakir.gradebook.entity.Course;
import com.bukeetcakir.gradebook.mapper.CourseMapper;
import com.bukeetcakir.gradebook.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<CourseResponse> getAllCourses() {
        var courses = courseRepository.findAll();
        return CourseMapper.INSTANCE.toCourseResponses(courses);
    }

    public CourseResponse getCourseById(Long courseId) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return CourseMapper.INSTANCE.toCourseResponse(course);
    }

    public CourseResponse saveCourse(CourseSaveRequest request) {
        if (isCourseAlreadyExist(request)) {
            throw new RuntimeException("Course already exist");
        }
        var course = CourseMapper.INSTANCE.toCourse(request);
        courseRepository.save(course);
        return CourseMapper.INSTANCE.toCourseResponse(course);
    }

    public CourseResponse updateCourse(Long courseId, CourseSaveRequest request) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setName(request.name());
        courseRepository.save(course);
        return CourseMapper.INSTANCE.toCourseResponse(course);
    }

    public void deleteCourse(Long courseId) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.deleteById(course.getId());
    }

    private boolean isCourseAlreadyExist(CourseSaveRequest request) {
        var course = courseRepository.findCourseByName(request.name());
        return course != null;
    }


}
