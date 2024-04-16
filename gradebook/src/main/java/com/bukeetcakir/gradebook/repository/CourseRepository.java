package com.bukeetcakir.gradebook.repository;

import com.bukeetcakir.gradebook.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
