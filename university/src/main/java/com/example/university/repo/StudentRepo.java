package com.example.university.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
  
  List<Student> findByFullTime(boolean fullTime);
  
  List<Student> findByAge(Integer age);
  
  List<Student> findByAttendeeLastName(String lastName);

}
