/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foreignstep.hibernatedemo.test;

import com.foreignstep.hibernatedemo.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

import org.junit.Ignore;

/**
 * @author HP
 */
public class StudentDaoTest {


    private static Validator validator;

    public StudentDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class StudentDao.
     */
    @org.junit.Test
    public void testCreate() {
        System.out.println("create");
        Student student = new Student(1234l, new Name("John", "Doe"), LocalDate.of(2001, Month.JUNE, 2), "oby@dg.com", new ArrayList<>(), Gender.MALE);
        student.getPhoneList().add("1188654");
        student.getPhoneList().add("2288694");

        StudentDao instance = new StudentDao();

        Set<ConstraintViolation<Student>> validate = null;
        try {
            validate = validator.validate(student);
            instance.create(student);
        } catch (Exception e) {
            assertEquals(1, validate.size());
            validate.forEach(v -> System.err.printf("Message [%s]\n", v.getMessage()));
        }


    }

    /**
     * Test of retrieve method, of class StudentDao.
     */
    //Make sure that this test passes
    @org.junit.Test
    @Ignore
    public void testRetrieveAll() {
        System.out.println("retrieve All");
        StudentDao instance = new StudentDao();
        Student student1 = new Student(1236l, new Name("John", "Doe"), LocalDate.of(2001, Month.JUNE, 20), "nobody@nowhere.com", new ArrayList<>(), Gender.MALE);
        Student student2 = new Student(1638l, new Name("Jane", "Koe"), LocalDate.of(2000, Month.MARCH, 6), "nobody@nowhere.com", new ArrayList<>(), Gender.FEMALE);
        instance.create(student1);
        instance.create(student2);
        List<Student> studentList = instance.retrieve();
        assertEquals(3, studentList.size());
        assertEquals(student1, studentList.get(0));
        assertEquals(student2, studentList.get(1));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieve method, of class StudentDao.
     */
    @org.junit.Test
    @Ignore
    public void testRetrieveById() {
        System.out.println("retrieve by id");
        StudentDao instance = new StudentDao();
        Long longId = 9311442l;
        Student student1 = new Student(longId, new Name("Bela", "Bose"), LocalDate.of(2000, Month.MARCH, 6), "nobody@nowhere.com", new ArrayList<>(), Gender.FEMALE);
        student1.getPhoneList().add("2441139");
        instance.create(student1);
        Student student2 = instance.retrieve(longId);
        assertEquals(student1, student2);
    }
}
