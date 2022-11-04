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

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    EnrollmentRepo enrollmentRepo;

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
    @GetMapping("page")
    public List<Course> getAllCoursesbyPage(){
        List<Course> courses = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 4);
        System.out.println(" = " + adminRepo.findById(1L).get().getFullName());
        courseRepo.findAll(pageable).forEach((course -> {
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

    @PostMapping("addcourse/{id}")
    public Course addCourse(@RequestBody Course course,@PathVariable Long id){
        Teacher teacher = teacherRepo.getById(id);
        Course courseToAdd = new Course(null,course.getNom(),course.getDescription(),course.getOpenDate(),course.getCloseDate(),null,teacher);
        courseRepo.save(courseToAdd);
        return new Course(courseToAdd.getId(),courseToAdd.getNom(),courseToAdd.getDescription(),courseToAdd.getOpenDate(),courseToAdd.getCloseDate());
    }

    @GetMapping("enrollments/{id}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long id) {
        System.out.println("id = " + id);
        Student student = studentRepo.getById(id);
        System.out.println("student.getFullName() = " + student.getFullName());
        List<Enrollment> enrollmentList = new ArrayList<>();
        System.out.println("enrollmentRepo.findAllByStudent(student).get(0).getCourse().getNom() = " + enrollmentRepo.findAllByStudent(student).get(0).getCourse().getNom());
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

    @Transactional
    @DeleteMapping("delete/{id}")
    public void deleteCourse(@PathVariable Long id){
        enrollmentRepo.deleteAllByCourse(courseRepo.getById(id));
        courseRepo.deleteById(id);
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

    @PostMapping("enrollcourse/{idStudent}")
    public void enrollCourse(@PathVariable Long idStudent, @RequestBody Course course){
        Enrollment enrollment = new Enrollment(null,studentRepo.getById(idStudent),courseRepo.getById(course.getId()), LocalDate.now());
        enrollmentRepo.save(enrollment);

    }



}
