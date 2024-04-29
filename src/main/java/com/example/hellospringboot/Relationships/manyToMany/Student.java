package com.example.hellospringboot.Relationships.manyToMany;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="rel_student")
public class Student {
    @Id
    Long studentId;

    @ManyToMany
    @JoinTable(name = "join_student_course",
            joinColumns = @JoinColumn(name = "std_id", referencedColumnName = "studentId" ),
            inverseJoinColumns = @JoinColumn(name = "crs_id", referencedColumnName = "courseId"))
    List<Course> course;
}
