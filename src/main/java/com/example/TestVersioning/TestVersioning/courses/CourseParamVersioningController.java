package com.example.TestVersioning.TestVersioning.courses;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseParamVersioningController {

    private static final List<Course> courses = new ArrayList<>();

    static {
        courses.add(new Course("1", "Spring Boot Basics", "Learn Spring Boot", "Backend"));
        courses.add(new Course("2", "React JS", "Learn React", "Frontend"));
        courses.add(new Course("3", "Docker Fundamentals", "Learn Docker", "DevOps"));
    }

    // Version 1
    @GetMapping
    public ResponseEntity<?> getCourses(@RequestParam(name = "version") String version) {

        if (version.equals("1")) {
            List<Object> v1Courses = new ArrayList<>();
            for (Course c : courses) {
                v1Courses.add(new Object() {
                    public final String title = c.getTitle();
                    public final String description = c.getDescription();
                });
            }
            return ResponseEntity.ok(v1Courses);
        }
        else if (version.equals("2")) {
            return ResponseEntity.ok(courses);
        }
        else {
            throw new UnsupportedApiVersionException("API version v" + version + " is not supported.");
        }
    }

}
