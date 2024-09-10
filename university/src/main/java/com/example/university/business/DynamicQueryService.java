package com.example.university.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.university.domain.Course;
import com.example.university.repo.CourseRepo;

import jakarta.persistence.criteria.Predicate;

@Service
public class DynamicQueryService {

    private CourseRepo courseRepo;

    public DynamicQueryService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> filterBySpecification(CourseFilter filter) {
        return courseRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filter.getDepartment().ifPresent(d -> predicates.add(criteriaBuilder.equal(root.get("department"), d)));
            filter.getCredits().ifPresent(c -> predicates.add(criteriaBuilder.equal(root.get("credits"), c)));
            filter.getInstructor().ifPresent(i -> predicates.add(criteriaBuilder.equal(root.get("instructor"), i)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
