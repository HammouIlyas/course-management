package com.bezkoder.springjwt.models.metier;

import com.bezkoder.springjwt.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Admin extends User {

    @OneToMany
    private List<User> usersList = new ArrayList<>();
}
