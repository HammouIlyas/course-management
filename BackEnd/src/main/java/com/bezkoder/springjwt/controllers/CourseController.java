package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.*;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("page")
    public List<Course> getAllCoursesbyPage(){
        List<Course> courses = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        //System.out.println(" = " + adminRepo.findById(1L).get().getFullName());
        courseRepo.findAll(pageable).forEach((course -> {
            Course course1 = new Course(course.getId(),course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate());
            courses.add(course1);
        }));
        return courses;
    }
}
