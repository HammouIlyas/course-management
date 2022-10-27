package com.bezkoder.springjwt.models.metier;

import com.bezkoder.springjwt.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends User{
    @OneToMany
    private List<Enrollment> enrollmentList = new ArrayList<>();

    public Student(String username, String email, String password, String fullName) {
        super(username, email, password,fullName);
    }

}
