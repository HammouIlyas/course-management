package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseRepo courseRepo;

    @GetMapping("list")
    public List<Course> getAllCourses(){
        //List<Course> courses = new ArrayList<>();
        return courseRepo.findAll();
    }

}
