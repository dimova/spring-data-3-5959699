package com.example.university.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JPA Entity for a Department of study at the University.
 *
 * Created by maryellenbowman
 */
@Entity
@Table(name="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="chair_id")
    private Staff chair;

    public Department(String name, Staff chair) {
        this.name = name;
        this.chair = chair;
    }

    protected Department() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Staff getChair() {
        return chair;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChair(Staff chair) {
        this.chair = chair;
    }

// //   public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }

    @Override
    public String toString() {
        return "Department{" +
                "chair=" + chair +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
