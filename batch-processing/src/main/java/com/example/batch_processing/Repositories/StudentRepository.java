package com.example.batch_processing.Repositories;

import com.example.batch_processing.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}