/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HP
 */
public class DBConsoleDemo {
    /**
     * @param args the command line arguments
     */
    public void insertStudent(Student student) {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO student VALUES('" + student.getId() + "', '" + student.getName() + "')");

        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Student> retrieveStudents() {
        List<Student> studentList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();

            for (int i = 0; i < 1000; i++) {
                connection = DBConnection.getConnection();
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                //System.out.printf("%s %s\n", id, name);
                Student student = new Student(id, name);
                studentList.add(student);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    public void propertiesDemo() {
        try {
            FileReader fileReader = new FileReader("db.properties");
            Properties properties = new Properties();
            properties.load(fileReader);

            String userName = properties.getProperty("username");
            System.out.printf("Username: %s\n", userName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBConsoleDemo() {
        //faculty();
        //student();
        //course();
        //section();
        registration();
    }

    private void faculty() {
        FacultyDAO facultyDAO = new FacultyDAOMySQLImplementation();

        Faculty faculty = new Faculty("RQ", "Romasa Qasim", "Junior Faculty");
        System.out.printf("Inserting [%s]\n", faculty);
        Faculty createdFaculty = facultyDAO.create(faculty);
        System.out.printf("Inserted [%s]\n", createdFaculty);
        facultyDAO
                .retrieve()
                .stream()
                .forEach(System.out::println);
        System.out.printf("Number of students: %d\n", facultyDAO.count());
        System.out.printf("Faculty by initials: [%s] [%s]\n", "JHC", facultyDAO.retrieve("JHC"));
    }

    private void student() {

        //insertStudent("Ak47", "Ak47 Oishi");
        //retrieveStudents().stream().forEach(System.out::println);
        StudentDAO studentDAO = new StudentDAOMySQLImplementation();
        //StudentDAO studentDAO = new StudentDAOFileImplementation();

        Random random = new Random();
        Student student = new Student(String.format("%013d", random.nextInt()), "Mrs.Mofiz");
        System.out.printf("Inserting [%s]\n", student);
        Student createdStudent = studentDAO.create(student);
        System.out.printf("Inserted [%s]\n", createdStudent);
        //studentDAO.retrieve(student -> student.getId().startsWith("17"))
        studentDAO
                .retrieve()
                .stream()
                .forEach(System.out::println);
        System.out.printf("Number of students: %d\n", studentDAO.count());

        //System.out.printf("Student by id: [%s] [%s]", 1730400, studentDAO.retrieve("1730400"));
        // propertiesDemo();
    }

    private void course() {
        CourseDAO courseDAO = new CourseDAOMySQLImplementation();

        Course course = new Course("CSC101L", "Introduction to computer programming lab", 1);
        System.out.printf("Inserting [%s]\n", course);
        Course createdCourse = courseDAO.create(course);
        System.out.printf("Inserted [%s]\n", createdCourse);
        courseDAO
                .retrieve()
                .stream()
                .forEach(System.out::println);
        System.out.printf("Number of courses: %d\n", courseDAO.count());
        //System.out.printf("Course by Code: [%s] [%s]\n", "CSC201", courseDAO.retrieve("CSC201"));
    }

    private void section() {

        SectionDAO sectionDAO = new SectionDAOMySQLImplementation();

        Section section = new Section(5, 3, 55, 50, "CSC211", "JHC");
        System.out.printf("Inserting [%s]\n", section);
        Section createdSection = sectionDAO.create(section);
        System.out.printf("Inserted [%s]\n", createdSection);
        sectionDAO
                .retrieve()
                .stream()
                .forEach(System.out::println);
        System.out.printf("Number of sections: %d\n", sectionDAO.count());
        //System.out.printf("Course by Code: [%s] [%s]\n", "CSC101", sectionDAO.retrieve(1));
    }

    private void registration() {

        RegistrationDAO registrationDAO = new RegistrationDAOMySQLImplementation();

        Registration registration = new Registration("7691", 5);
        System.out.printf("Inserting [%s]\n", registration);
        Registration createdRegistration = registrationDAO.create(registration);
        System.out.printf("Inserted [%s]\n", createdRegistration);
        registrationDAO
                .retrieve()
                .stream()
                .forEach(System.out::println);
        System.out.printf("Number of sections: %d\n", registrationDAO.count());
        System.out.printf("Registartion  by student ID : [%s] [%s]\n", "7691", registrationDAO.retrieve("7691"));
    }

    public static void main(String[] args) {
        new DBConsoleDemo();
    }

}
