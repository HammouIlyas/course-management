package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.metier.*;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.bezkoder.springjwt.models.ERole.*;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	TeacherRepo teacherRepo;

	@Autowired
	EnrollmentRepo enrollmentRepo;




	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner() {
		return (args) -> {
			roleRepository.save(new Role(ROLE_ADMIN));
			roleRepository.save(new Role(ROLE_STUDENT));
			roleRepository.save(new Role(ROLE_TEACHER));
			Admin user1 = new Admin("nouhaila-benlguarni@gmail.com","nouhaila-benlguarni@gmail.com", encoder.encode("password123"),"Nouhaila Benlguarni");
			Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			user1.setRoles(roles);
			adminRepo.save(user1);

			Teacher teacher1 = new Teacher("hanae-alaoui@gmail.com","hanae-alaoui@gmail.com",encoder.encode("password123"),"Hanae Alaoui");
			Set<Role> roles2 = new HashSet<>();
			roles2.add(roleRepository.findByName(ROLE_TEACHER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			teacher1.setRoles(roles2);
			teacherRepo.save(teacher1);

			Student student1 = new Student("mouad-benlguarni@gmail.com","mouad-benlguarni@gmail.com",encoder.encode("password123"),"mouad benlguarni");
			Set<Role> roles3 = new HashSet<>();
			roles3.add(roleRepository.findByName(ROLE_STUDENT).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			student1.setRoles(roles3);
			studentRepo.save(student1);

			Teacher teacher2 = new Teacher("soussi-alaoui@gmail.com","soussi-alaoui@gmail.com",encoder.encode("password123"),"soussi Alaoui");
			Set<Role> roles4 = new HashSet<>();
			roles4.add(roleRepository.findByName(ROLE_TEACHER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			teacher2.setRoles(roles4);
			teacherRepo.save(teacher2);

			Course course1 = new Course(null,"JAVA 1/2","Cours de programmation Java pour débutants", LocalDate.of(2022,10,10),LocalDate.of(2022,11,10),null,teacher1);
			Course course2 = new Course(null,"SQL 1/2","Découvrez les compétences SQL essentielles ",LocalDate.of(2022,10,11),LocalDate.of(2022,11,11),null,teacher1);
			Course course11 = new Course(null,"JAVA2/2","Cours de programmation Java pour débutants (suite)", LocalDate.of(2022,10,10),LocalDate.of(2022,11,10),null,teacher1);
			Course course22 = new Course(null,"SQL2/2","Découvrez les compétences SQL essentielles (suite)",LocalDate.of(2022,10,11),LocalDate.of(2022,11,11),null,teacher1);
			Course course3 = new Course(null,"Python","Ce cours Python est pour les débutants",LocalDate.of(2022,10,12),LocalDate.of(2022,11,12),null,teacher2);
			Course course4 = new Course(null,"Data Science","Développez des compétences en science des données",LocalDate.of(2022,10,13),LocalDate.of(2022,11,13),null,teacher2);
			courseRepo.save(course1);
			courseRepo.save(course2);
			courseRepo.save(course11);
			courseRepo.save(course22);
			courseRepo.save(course3);
			courseRepo.save(course4);
			teacher1.setCourses(Arrays.asList(course1,course2,course3,course4));
			userRepository.save(teacher1);
			Enrollment enrollment = new Enrollment(null,student1,course1,LocalDate.of(2022,10,15));
			enrollmentRepo.save(enrollment);
		};
	};


}
