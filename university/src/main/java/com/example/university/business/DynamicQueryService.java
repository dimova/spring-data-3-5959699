package com.example.university.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.university.domain.Course;
import com.example.university.repo.CourseQueryDslRepo;
import com.example.university.repo.CourseRepo;
import com.querydsl.core.BooleanBuilder;

import jakarta.persistence.criteria.Predicate;

import static com.example.university.domain.QCourse.course;

@Service
public class DynamicQueryService {

    private CourseRepo courseRepo;

    private CourseQueryDslRepo queryDslRepo;

    public DynamicQueryService(CourseRepo courseRepo, CourseQueryDslRepo queryDslRepo) {
        this.courseRepo = courseRepo;
        this.queryDslRepo = queryDslRepo;
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

    public List<Course> filterByQueryDsl(CourseFilter filter) {
        List<Course> courses = new ArrayList<>();
        queryDslRepo
                .findAll(getQueryDslPredicate(filter))
                .forEach(courses::add);
        return courses;
    }
    
    private com.querydsl.core.types.Predicate getQueryDslPredicate(CourseFilter filter) {
        BooleanBuilder predicate = new BooleanBuilder();
        filter.getDepartment().ifPresent(d -> predicate.and(course.department.eq(d)));
        filter.getCredits().ifPresent(c -> predicate.and(course.credits.eq(c)));
        filter.getInstructor().ifPresent(i -> predicate.and(course.instructor.eq(i)));
        return predicate;
    }
}
