package com.example.college.repo;
import com.example.college.domain.Staff;

import reactor.core.publisher.Flux;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StaffRepo extends ReactiveCrudRepository<Staff, String>{
  
  Flux<Staff> findByMemberLastName(String lastName);
}
