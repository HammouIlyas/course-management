package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.models.metier.Enrollment;
import com.bezkoder.springjwt.models.metier.Student;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
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
        //System.out.println("id = " + id);
        Student student = studentRepo.getById(id);
        //System.out.println("student.getFullName() = " + student.getFullName());
        List<Enrollment> enrollmentList = new ArrayList<>();
        //System.out.println("enrollmentRepo.findAllByStudent(student).get(0).getCourse().getNom() = " + enrollmentRepo.findAllByStudent(student).get(0).getCourse().getNom());
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
    @GetMapping("allcourses")
    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
//        //System.out.println(" = " + adminRepo.findById(1L).get().getFullName());
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
