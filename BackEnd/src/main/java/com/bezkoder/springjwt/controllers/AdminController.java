package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.metier.Student;
import com.bezkoder.springjwt.models.metier.Teacher;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
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


    @GetMapping("teachers")
    public List<Teacher> getAlTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        teacherRepo.findAll().forEach(teacher -> {
            Teacher teacher1 = new Teacher(teacher.getFullName(),teacher.getUsername(),null,teacher.getFullName());
            teacher1.setId(teacher.getId());
            teachers.add(teacher1);
        });
        return teachers;
    }

    @GetMapping("students")
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        studentRepo.findAll().forEach(student -> {
            Student student1 = new Student(student.getFullName(),student.getUsername(),null,student.getFullName());
            student1.setId(student.getId());
            students.add(student1);
        });
        return students;
    }

    @Transactional
    @DeleteMapping("deletestudent/{id}")
    public void deleteStudent(@PathVariable Long id){
        enrollmentRepo.deleteAllByStudent(studentRepo.getById(id));
        studentRepo.deleteById(id);
    }

    @Transactional
    @DeleteMapping("deleteteacher/{id}")
    public void deleteTeacher(@PathVariable Long id){
        teacherRepo.getById(id).getCourses().forEach(course -> {
            enrollmentRepo.deleteAllByCourse(course);
        });
        courseRepo.deleteAllByOwner(teacherRepo.getById(id));
        teacherRepo.deleteById(id);
    }
}
