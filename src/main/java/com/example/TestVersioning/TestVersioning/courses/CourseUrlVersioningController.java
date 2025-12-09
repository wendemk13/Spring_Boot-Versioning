package com.example.TestVersioning.TestVersioning.courses;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseUrlVersioningController {

    private static final List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("1", "Spring Boot Basics", "Learn Spring Boot", "Backend"));
        courses.add(new Course("2", "React JS", "Learn React", "Frontend"));
        courses.add(new Course("3", "Docker Fundamentals", "Learn Docker", "DevOps"));
    }

    // Version 1: only title + description
    @GetMapping("/api/v1/courses")
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

    // Version 2: full data
    @GetMapping("/api/v2/courses")
    public ResponseEntity<List<Course>> getCoursesV2() {
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/api/v{version}/courses")
    public ResponseEntity<?> fallbackVersion(@PathVariable String version) {

        // Allow v1 and v2 only
        if (!version.equals("1") && !version.equals("2")) {
            throw new UnsupportedApiVersionException("v" + version);
        }

        // Default behavior if version is valid (optional)
        return ResponseEntity.badRequest().body("Invalid request format.");
    }
}
