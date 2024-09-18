package com.example.college.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.college.domain.Department;

import reactor.core.publisher.Mono;

public interface DepartmentRepo extends ReactiveCrudRepository<Department, String>{
  
  Mono<Department> findByName(String name);

  Mono<List<Department>> findByChairId(String chairId);

  @Query("{ 'name' : { $regex: ?0 } }")
  Mono<List<Department>> findNameByPattern(String pattern);
}
