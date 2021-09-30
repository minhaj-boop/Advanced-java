/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foreignstep.hibernatedemo;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author HP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Student {
    @Id
    private long id;
    private Name name;
    private LocalDate dob;
    @Size(min = 5, max = 10, message = "Email address must be between 5 to 10 characters long")
    //@Email(message = "Email address must be a valid email address")
    private String email;
    @ElementCollection
    private List<String> phoneList;
    //TODO create a list of addresses. You'd have to create your own Address calss
    //TODO add proper test cases to ensure that everything works
    //TODO add a gender enamuration to record the gender of a student (follow the hibernate user guide)
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
