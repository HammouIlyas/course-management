package com.bezkoder.springjwt.models.metier;

import com.bezkoder.springjwt.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends User {
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>() ;

    public Teacher(String username, String email, String password, String fullName) {
        super(username, email, password,fullName);
    }
}
