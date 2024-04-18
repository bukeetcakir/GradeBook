package com.bukeetcakir.gradebook.repository;

import com.bukeetcakir.gradebook.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByNumber(String number);

}
