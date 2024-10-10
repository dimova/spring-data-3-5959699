package com.example.college;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private Mono<Staff> saveStaffPublisher(Staff staff) {
        return staffRepo.save(staff);
    }

    private Flux<Department> saveDepartmentsPublisher(List<Department> departments) {
        return departmentRepo.saveAll(departments);
    }
    @Override
    public void run(String... args) {
    
        Mono<Staff> deanJonesMono = 
            saveStaffPublisher(new Staff(new Person("John", "Jones")));
        Mono<Staff> deanMartinMono = saveStaffPublisher(new Staff(new Person("John", "Martin")));
        Staff deanJones = deanJonesMono.block();
        
        
        saveDepartmentsPublisher(
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

    @GetMapping("/staff/search/byLastName/{lastName}")
    public Flux<Staff> findByLastName(String lastName) {
        return staffRepo.findByMemberLastName(lastName);
    }
    
    @PostMapping("/staff")
    public Mono<Staff> createStaff(@RequestBody Staff staff) {
        return staffRepo.save(staff);
    }

    @GetMapping("/staff/{id}")
    public Mono<Staff> getStaffById(String id) {
        return staffRepo.findById(id);
    }

    @GetMapping("/departments")
    public Flux<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @PostMapping("/departments")
    public Mono<Department> createDepartment(@RequestBody Department department) {
        return departmentRepo.save(department);
    }

    @GetMapping("/departments/{id}")
    public Mono<Department> getDepartmentById(String id) {
        return departmentRepo.findById(id);
    }

    @GetMapping("/departments/search/byName/{name}") 
    public  Mono<Department> findByName(String name) {
        return departmentRepo.findByName(name);
    }

    @GetMapping("/departments/search/byCharId/{chairId}")
    public Flux<Department> findByChairId(String chairId) {
        return departmentRepo.findByChairId(chairId);
    }

    @GetMapping("/departments/search/byNameLike/{pattern}")
    public Flux<Department> findNameByPattern(String pattern) {
        return departmentRepo.findNameByPattern(pattern);
    }
}
