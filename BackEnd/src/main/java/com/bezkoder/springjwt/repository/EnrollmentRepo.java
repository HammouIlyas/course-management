package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.metier.Enrollment;
import com.bezkoder.springjwt.models.metier.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findAllByStudent(Student student);
}
