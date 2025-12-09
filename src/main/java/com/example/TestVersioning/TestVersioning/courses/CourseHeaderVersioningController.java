package com.example.TestVersioning.TestVersioning.courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/header/courses")
public class CourseHeaderVersioningController {

    // Static sample data
    private static final List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("1", "Spring Boot Basics", "Learn Spring Boot", "Backend"));
        courses.add(new Course("2", "React JS", "Learn React", "Frontend"));
        courses.add(new Course("3", "Docker Fundamentals", "Learn Docker", "DevOps"));
    }

    // Header Versioning: X-API-VERSION
    @GetMapping
    public ResponseEntity<?> getCourses(@RequestHeader("X-API-VERSION") int version) {
        if (version == 1) {
            // Version 1: return only title and description
            List<Object> v1Courses = new ArrayList<>();
            for (Course c : courses) {
                v1Courses.add(new Object() {
                    public final String title = c.getTitle();
                    public final String description = c.getDescription();
                });
            }
            return ResponseEntity.ok(v1Courses);
        } else if (version == 2) {
            // Version 2: return full course data
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.badRequest().body("Invalid API Version");
        }
    }
}
