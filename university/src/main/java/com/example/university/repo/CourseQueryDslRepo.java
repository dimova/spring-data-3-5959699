package com.example.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.university.domain.Course;

public interface CourseQueryDslRepo extends JpaRepository<Course, Integer>, QuerydslPredicateExecutor<Course> {
  
}
