package com.bukeetcakir.gradebook.repository;

import com.bukeetcakir.gradebook.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
