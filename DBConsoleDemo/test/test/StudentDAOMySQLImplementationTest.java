/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dbconsoledemo.Student;
import dbconsoledemo.StudentDAO;
import dbconsoledemo.StudentDAOMySQLImplementation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author HP
 */
public class StudentDAOMySQLImplementationTest {

    private static StudentDAO studentDAO;

    public StudentDAOMySQLImplementationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Preparing to run all test...");
        studentDAO = new StudentDAOMySQLImplementation();

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("help!!!!!!!!!!!!");
        studentDAO.deletAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {

        System.out.println("testing create method...");

        Student student = new Student("6710", "solosolo");

        Student createdStudent = studentDAO.create(student);

        assertEquals(student, createdStudent);
    }

    @Test
    public void CreateStudentsWithLongId() {

        System.out.println("testing create method...");
        // TODO expect a VaitadtionException to occur if the ID is too long
        Student student = new Student("67142432432533321230", "solosolo");

        Student createdStudent = studentDAO.create(student);

        assertEquals(student, createdStudent);
    }

    @Test
    public void testRetrieveById() {

        System.out.println("testing retreive by id method...");

        Student student = new Student("7691", "solosolo");

        studentDAO.create(student);

        assertEquals(student, studentDAO.retrieve(student.getId()));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
