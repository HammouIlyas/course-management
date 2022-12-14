package com.bezkoder.springjwt.models.metier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private LocalDate openDate;
    private LocalDate closeDate;
    @OneToMany
    private List<KeyWord> keywords = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher owner;

    public Course(String nom, String description,/* LocalDate openDate, LocalDate closeDate,*/ Teacher owner) {
        this.nom = nom;
        this.description = description;
//        this.openDate = openDate;
//        this.closeDate = closeDate;
        this.owner = owner;
    }

    public Course(Long id, String nom, String description, LocalDate openDate, LocalDate closeDate) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }
}
