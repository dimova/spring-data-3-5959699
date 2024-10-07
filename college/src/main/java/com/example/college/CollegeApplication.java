package com.example.college;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.college.domain.Department;
import com.example.college.domain.Person;
import com.example.college.domain.Staff;
import com.example.college.repo.DepartmentRepo;
import com.example.college.repo.StaffRepo;

@SpringBootApplication
public class CollegeApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private StaffRepo staffRepo;

    @Transactional
    private Staff saveStaff(Staff staff) {
    return staffRepo.save(staff);
    }

    @Transactional
    private List<Department> saveDepartments(List<Department> departments) {
    return departmentRepo.saveAll(departments);
    }

    @Override
    public void run(String... args) {
    Staff deanJones = saveStaff(new Staff( new Person("John", "Jones")));
    Staff deanMartin = saveStaff(new Staff(new Person("John", "Martin")));

    saveDepartments(
    Arrays.asList(new Department("Humanities", deanJones),
    new Department("Natural Sciences", deanMartin),
    new Department("Social Sciences", deanJones)));
    }
}
