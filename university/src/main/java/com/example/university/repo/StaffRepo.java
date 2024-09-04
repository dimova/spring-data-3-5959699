package com.example.university.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.university.domain.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

  @Query("SELECT s FROM Staff s WHERE s.member.lastName = :lastName")
  List<Staff> findByLastName(@Param("lastName") String lastName);

}
