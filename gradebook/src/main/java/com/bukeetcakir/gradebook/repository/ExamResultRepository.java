package com.bukeetcakir.gradebook.repository;

import com.bukeetcakir.gradebook.dto.examResult.ExamResultResponse;
import com.bukeetcakir.gradebook.entity.Course;
import com.bukeetcakir.gradebook.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    int countExamResultByStudentIdAndCourseId(Long studentId, Long courseId);


    @Query("SELECT er.score FROM ExamResult er WHERE er.studentId = :studentId AND er.courseId = :courseId")
    List<Integer> findScoresByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Query("SELECT DISTINCT er.courseId FROM ExamResult er WHERE er.studentId = :studentId")
    List<Long> findCourseIdsByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT AVG(er.score) FROM ExamResult er WHERE er.studentId = :studentId AND er.courseId = :courseId")
    Double getAverageScoreByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

}
