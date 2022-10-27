package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.models.metier.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    //Optional<Course> findByOwner(Teacher teacher);


    List<Course> findAllByOwner(Teacher teacher);
}
