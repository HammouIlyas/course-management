package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.models.metier.Teacher;
import com.bezkoder.springjwt.repository.AdminRepo;
import com.bezkoder.springjwt.repository.CourseRepo;
import com.bezkoder.springjwt.repository.TeacherRepo;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseRepo courseRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    UserRepository userRepository;

    @GetMapping("all")
    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        System.out.println(" = " + adminRepo.findById(1L).get().getFullName());
         courseRepo.findAll().forEach((course -> {
             Course course1 = new Course(course.getId(),course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate());
             courses.add(course1);
         }));
        return courses;
    }

    @GetMapping("list/{id}")
    public List<Course> getCoursesByTeacher(@PathVariable Long id){
        List<Course> courses = new ArrayList<>();
        courseRepo.findAllByOwner(teacherRepo.getById(id)).forEach((course -> {
            Course course1 = new Course(course.getId(),course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate());
            courses.add(course1);
        }));
        return courses;
    }

    @PostMapping("addcourse")
    public Course addCourse(@RequestBody Course course){
        Teacher teacher = teacherRepo.getById(course.getOwner().getId());
        Course courseToAdd = new Course(null,course.getNom(),course.getDescription(),null,null,null,teacher);
        courseRepo.save(courseToAdd);
        return new Course(courseToAdd.getId(),courseToAdd.getNom(),courseToAdd.getDescription(),courseToAdd.getOpenDate(),courseToAdd.getCloseDate());
    }


    //System.out.println("course owner = " + userRepository.findById(course.getOwner().getId()));
    //System.out.println("userRepository = " + teacherRepo.findById(2L).get().getFullName());
    //System.out.println("teacher.getFullName() = " + teacher.getFullName());

}
