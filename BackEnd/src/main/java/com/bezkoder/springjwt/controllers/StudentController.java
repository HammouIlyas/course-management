package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.models.metier.Enrollment;
import com.bezkoder.springjwt.models.metier.Student;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    @Autowired
    CourseRepo courseRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    EnrollmentRepo enrollmentRepo;

    /**
     * get enrollment list by student
    */
    @GetMapping("enrollments/{id}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long id) {
        Student student = studentRepo.getById(id);
        List<Enrollment> enrollmentList = new ArrayList<>();
        enrollmentRepo.findAllByStudent(student).forEach(enrollment -> {
            Course course = enrollment.getCourse();
            Course course1 = new Course(course.getId(),course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate());
            Enrollment enrollment1 = new Enrollment();
            enrollment1.setId(enrollment.getId());
            enrollment1.setEnrollmentDate(enrollment.getEnrollmentDate());
            enrollment1.setCourse(course1);
            enrollmentList.add(enrollment1);
        });
        return enrollmentList;
    }

    /**
     * get All the available courses
     */
    @GetMapping("courseslist")
    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        courseRepo.findAll().forEach((course -> {
            Course course1 = new Course(course.getId(),course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate());
            courses.add(course1);
        }));
        return courses;
    }

    /**
     * enroll a course by a student
     */
    @PostMapping("enrollcourse/{idStudent}")
    public void enrollCourse(@PathVariable Long idStudent, @RequestBody Course course){
        Enrollment enrollment = new Enrollment(null,studentRepo.getById(idStudent),courseRepo.getById(course.getId()), LocalDate.now());
        enrollmentRepo.save(enrollment);

    }
}
