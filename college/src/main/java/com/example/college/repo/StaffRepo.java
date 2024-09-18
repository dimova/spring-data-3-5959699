package com.example.college.repo;
import com.example.college.domain.Staff;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffRepo extends MongoRepository<Staff, String>{
  
  List<Staff> findByMemberLastName(String lastName);
}
