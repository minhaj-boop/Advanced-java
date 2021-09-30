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
public class FacultyDAOMySQLImplementation implements FacultyDAO {

    private Connection connection;
    private PreparedStatement preparedCreateStatement;
    private PreparedStatement preparedRetrieveByInitialsStatement;
    private PreparedStatement preparedRetrieveAllStatement;

    public FacultyDAOMySQLImplementation() {
        try {
            connection = DBConnection.getConnection();

            InputStream inputStream = getClass().getResourceAsStream("resources/query.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);

            Properties properties = new Properties();
            properties.load(fileReader);

            preparedCreateStatement = connection.prepareStatement(properties.getProperty("INSERT_FACULTY_QUERY"));
            preparedRetrieveByInitialsStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_FACULTY_BY_INITIALS_QUERY"));
            preparedRetrieveAllStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_ALL_FACULTY_QUERY"));
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Faculty create(Faculty faculty) {
        try {
            preparedCreateStatement.setString(1, faculty.getInitials());
            preparedCreateStatement.setString(2, faculty.getName());
            preparedCreateStatement.setString(3, faculty.getPosition());
            preparedCreateStatement.executeUpdate();

            return retrieve(faculty.getInitials());
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Faculty retrieve(String facultyInitials) {
        try {
            preparedRetrieveByInitialsStatement.setString(1, facultyInitials);
            ResultSet resultSet = preparedRetrieveByInitialsStatement.executeQuery();

            if (resultSet.next()) {
                Faculty faculty = new Faculty(resultSet.getString("initials"), resultSet.getString("name"), resultSet.getString("position"));
                return faculty;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Faculty> retrieve() {
        List<Faculty> facultyList = new ArrayList();

        try {
            ResultSet resultSet = preparedRetrieveAllStatement.executeQuery();
            while (resultSet.next()) {
                Faculty faculty = new Faculty(resultSet.getString("initials"), resultSet.getString("name"), resultSet.getString("position"));
                facultyList.add(faculty);
            }
            return facultyList;
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Faculty> retrieve(Predicate<Faculty> predicate) {
        return retrieve().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
