package com.example.college;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.college.domain.Department;
import com.example.college.domain.Person;
import com.example.college.domain.Staff;
import com.example.college.repo.DepartmentRepo;
import com.example.college.repo.StaffRepo;

@SpringBootApplication
public class CollegeApplication implements CommandLineRunner{

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private StaffRepo staffRepo;
    
    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Staff deanJones = staffRepo.save(new Staff( new Person("John", "Jones")));
        Staff deanMartin = staffRepo.save(new Staff(new Person("John", "Martin")));
  
        List<Department> departments = departmentRepo.saveAll(
                Arrays.asList(new Department("Humanities", deanJones),
                        new Department("Natural Sciences", deanMartin),
                        new Department("Social Sciences", deanJones)));
    }

    
}
