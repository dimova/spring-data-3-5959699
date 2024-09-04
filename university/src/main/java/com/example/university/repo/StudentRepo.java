package com.example.university.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
  
  List<Student> findByFullTime(boolean fullTime);
  
  List<Student> findByAge(Integer age);
  
  List<Student> findByAttendeeLastName(String lastName);
  
/*
  // Queries with clauses and expressions
  // findOldest
  Optional<Student> findTopByOrderByAgeDesc();

  // findByFirstAndLastName
  List<Student> findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);

  // findByAgeLessThan
  List<Student> findByAgeLessThan(int age);

  // findSimilarLastName
  List<Student> findByAttendeeLastNameLike(String nameCriteria);

  // findFirstInAlphabet
  Optional<Student> findFirstByOrderByAttendeeLastNameAsc();

  // find3Oldest
  List<Student> findTop3ByOrderByAgeDesc();
  */
}
