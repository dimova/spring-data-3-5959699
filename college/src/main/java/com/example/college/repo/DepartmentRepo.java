package com.example.college.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.college.domain.Department;

public interface DepartmentRepo extends MongoRepository<Department, String>{
  
  Optional<Department> findByName(String name);

  Optional<Department> findByChairId(String chairId);

  @Query("{ 'name' : { $regex: ?0 } }")
  List<Department> findNameByPattern(String pattern);
}
