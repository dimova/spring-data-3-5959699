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

@SpringBootApplication
public class CollegeApplication { 

    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }

}
