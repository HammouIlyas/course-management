package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.metier.Admin;
import com.bezkoder.springjwt.models.metier.Course;
import com.bezkoder.springjwt.models.metier.Student;
import com.bezkoder.springjwt.models.metier.Teacher;
import com.bezkoder.springjwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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

			Student student1 = new Student("ilyas-hammou@gmail.com","hanae-alaoui@gmail.com",encoder.encode("password123"),"Ilyas Hammou");
			Set<Role> roles3 = new HashSet<>();
			roles3.add(roleRepository.findByName(ROLE_STUDENT).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
			student1.setRoles(roles3);
			studentRepo.save(student1);

			Course course1 = new Course(null,"JAVA","Cours de programmation Java pour débutants en programmation orientée objet Java. BONUS : Construisez l'API REST avec Spring Boot.",null,null,null,teacher1);
			Course course2 = new Course(null,"SQL","Découvrez les compétences SQL essentielles nécessaires pour vous transformer en développeur SQL.",null,null,null,teacher1);
			Course course3 = new Course(null,"Python","Ce cours Python pour débutants vous apprend rapidement le langage Python. Inclut la formation en ligne Python avec Python.",null,null,null,teacher1);
			Course course4 = new Course(null,"Data Science","Développez des compétences en science des données, découvrez Python et SQL, analysez et visualisez des données et créez des modèles d’apprentissage automatique.",null,null,null,teacher1);
			courseRepo.save(course1);
			courseRepo.save(course2);
			courseRepo.save(course3);
			courseRepo.save(course4);
			teacher1.setCourses(Arrays.asList(course1,course2,course3,course4));
			userRepository.save(teacher1);
		};
	};


}
