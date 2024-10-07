package com.example.college;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.college.domain.Department;
import com.example.college.domain.Person;
import com.example.college.domain.Staff;
import com.example.college.repo.DepartmentRepo;
import com.example.college.repo.StaffRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class CollegeApplication implements CommandLineRunner{

    
    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private StaffRepo staffRepo;
        
    private Mono<Staff> saveStaff(Staff staff) {
    return staffRepo.save(staff);
    }

    private Flux<Department> saveDepartments(List<Department> departments) {
    return departmentRepo.saveAll(departments);
    }

    @Override
    public void run(String... args) {
    
        Mono<Staff> deanJonesMono = saveStaff(new Staff(new Person("John", "Jones")));
        Mono<Staff> deanMartinMono = saveStaff(new Staff(new Person("John", "Martin")));
        Staff deanJones = deanJonesMono.block();
        
        
        saveDepartments(
            List.of(new Department("Humanities", deanJones),
                    new Department("Natural Sciences", deanMartinMono.block()),
                    new Department("Social Sciences", deanJones)))
            .subscribe(d ->
             System.out.println(d));
    }

    @GetMapping("/staff")
    public Flux<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    @GetMapping("/departments")
    public Flux<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
}
