package com.bukeetcakir.gradebook.repository;

import com.bukeetcakir.gradebook.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult,Long> {
}
