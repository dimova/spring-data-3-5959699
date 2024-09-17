package com.example.college.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.college.domain.Staff;

import reactor.core.publisher.Flux;

/**
 * DataSource Management for the Staff at the University.
 * <p>
 * Created by maryellenbowman.
 */
public interface StaffRepo extends ReactiveCrudRepository<Staff, String> {

  Flux<Staff> findByMemberLastName(String lastName);

}