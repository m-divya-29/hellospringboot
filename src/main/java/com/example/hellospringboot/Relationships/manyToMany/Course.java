package com.example.hellospringboot.Relationships.manyToMany;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "rel_course")
public class Course {
    @Id
    Long courseId;

    @ManyToMany
            @JoinTable(name = "join_student_course",
            joinColumns = @JoinColumn(name = "crs_id", referencedColumnName = "courseId" ),
            inverseJoinColumns = @JoinColumn(name = "std_id", referencedColumnName = "studentId"))
    List<Student> students;
}
