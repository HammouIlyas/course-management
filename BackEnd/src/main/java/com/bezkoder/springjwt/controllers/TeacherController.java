package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.models.metier.Teacher;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
public class TeacherController {
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

    @GetMapping("list/{id}")
    public List<Course> getCoursesByTeacher(@PathVariable Long id){
        List<Course> courses = new ArrayList<>();
        courseRepo.findAllByOwner(teacherRepo.getById(id)).forEach((course -> {
            Course course1 = new Course(course.getId(),course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate());
            courses.add(course1);
        }));
        return courses;
    }

    @PostMapping("addcourse/{id}")
    public Course addCourse(@RequestBody Course course,@PathVariable Long id){
        Teacher teacher = teacherRepo.getById(id);
        Course courseToAdd = new Course(null,course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate(),null,teacher);
        courseRepo.save(courseToAdd);
        return new Course(courseToAdd.getId(),courseToAdd.getNom(),courseToAdd.getDescription(),courseToAdd.getOpenDate(),courseToAdd.getCloseDate());
    }

    @Transactional
    @PutMapping("updatecourse/{id}")
    public Course updateCourse(@PathVariable Long id,@RequestBody Course course){
        System.out.println("id = " + id);
        System.out.println("course = " + course);
        Course courseToUpdate = courseRepo.findById(id).get();
        courseToUpdate.setNom(course.getNom());
        courseToUpdate.setDescription(course.getDescription());
        courseToUpdate.setOpenDate(course.getOpenDate());
        courseToUpdate.setCloseDate(course.getCloseDate());
        courseRepo.save(courseToUpdate);
        //courseToUpdate.setOwner(null);
        return new Course(courseToUpdate.getId(), courseToUpdate.getNom(), courseToUpdate.getDescription(),courseToUpdate.getOpenDate(),courseToUpdate.getCloseDate());
    }

    @Transactional
    @DeleteMapping("delete/{id}")
    public String tryDeleteCourse(@PathVariable Long id){
        try {
            courseRepo.deleteById(id);
            return "true";
        }
        catch (Exception e) {
            return "false";
        }
    }

    @Transactional
    @DeleteMapping("deleteforced/{id}")
    public void forceDeleteCourse(@PathVariable Long id){
        enrollmentRepo.deleteAllByCourse(courseRepo.getById(id));
        courseRepo.deleteById(id);
    }
}
