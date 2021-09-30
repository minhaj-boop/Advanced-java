/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author HP
 */
public class CourseDAOMySQLImplementation implements CourseDAO {

    private Connection connection;
    private PreparedStatement preparedCreateStatement;
    private PreparedStatement preparedRetrieveByCodeStatement;
    private PreparedStatement preparedRetrieveAllStatement;

    public CourseDAOMySQLImplementation() {
        try {
            connection = DBConnection.getConnection();

            InputStream inputStream = getClass().getResourceAsStream("resources/query.properties");
            InputStreamReader fileReade = new InputStreamReader(inputStream);

            Properties properties = new Properties();
            properties.load(fileReade);

            preparedCreateStatement = connection.prepareStatement(properties.getProperty("INSERT_COURSE_QUERY"));
            preparedRetrieveByCodeStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_COURSE_BY_CODE_QUERY"));
            preparedRetrieveAllStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_ALL_COURSE_QUERY"));

        } catch (IOException ex) {
            Logger.getLogger(CourseDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            preparedCreateStatement.setString(1, course.getCode());
            preparedCreateStatement.setString(2, course.getTitle());
            preparedCreateStatement.setDouble(3, course.getCredits());
            preparedCreateStatement.executeUpdate();

            return retrieve(course.getCode());
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Course retrieve(String courseCode) {
        try {
            preparedRetrieveByCodeStatement.setString(1, courseCode);
            ResultSet resultSet = preparedRetrieveByCodeStatement.executeQuery();

            if (resultSet.next()) {
                Course course = new Course(resultSet.getString("code"), resultSet.getString("title"), resultSet.getDouble("credits"));
                return course;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> retrieve() {
        List<Course> courseList = new ArrayList();

        try {
            ResultSet resultSet = preparedRetrieveAllStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString("code"), resultSet.getString("title"), resultSet.getDouble("credits"));
                courseList.add(course);
            }
            return courseList;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> retrieve(Predicate<Course> predicate) {
        return retrieve().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
