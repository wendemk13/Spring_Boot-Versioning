package com.example.TestVersioning.TestVersioning.courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/media/courses")
public class CourseMediaVersioningController {

    // Static sample data
    private static final List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("1", "Spring Boot Basics", "Learn Spring Boot", "Backend"));
        courses.add(new Course("2", "React JS", "Learn React", "Frontend"));
        courses.add(new Course("3", "Docker Fundamentals", "Learn Docker", "DevOps"));
    }

    // Version 1: only title and description
    @GetMapping(produces = "application/vnd.example.v1+json")
    public ResponseEntity<List<Object>> getCoursesV1() {
        List<Object> v1Courses = new ArrayList<>();
        for (Course c : courses) {
            v1Courses.add(new Object() {
                public final String title = c.getTitle();
                public final String description = c.getDescription();
            });
        }
        return ResponseEntity.ok(v1Courses);
    }

    // Version 2: full course data
    @GetMapping(produces = "application/vnd.example.v2+json")
    public ResponseEntity<List<Course>> getCoursesV2() {
        return ResponseEntity.ok(courses);
    }

    // handle exception
    @GetMapping(produces = "*/*")
    public void handleUnsupportedVersion() {
        throw new UnsupportedApiVersionException("undefined");
    }

}
