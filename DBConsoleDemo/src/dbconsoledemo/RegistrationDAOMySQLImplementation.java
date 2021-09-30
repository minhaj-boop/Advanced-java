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
public class RegistrationDAOMySQLImplementation implements RegistrationDAO {

    Connection connection;

    PreparedStatement preparedCreateStatement;
    PreparedStatement preparedRetrieveByIdStatement;
    PreparedStatement preparedRetrieveAllStatement;

    public RegistrationDAOMySQLImplementation() {
        try {
            connection = DBConnection.getConnection();

            InputStream inputStream = getClass().getResourceAsStream("resources/query.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);

            Properties properties = new Properties();
            properties.load(fileReader);

            preparedCreateStatement = connection.prepareStatement(properties.getProperty("INSERT_REGISTRATION_QUERY"));
            preparedRetrieveByIdStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_REGISTRATION_BY_STUDENTID_QUERY"));
            preparedRetrieveAllStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_ALL_REGISTRATION_QUERY"));
        } catch (IOException ex) {
            Logger.getLogger(RegistrationDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Registration create(Registration registration) {
        try {
            preparedCreateStatement.setString(1, registration.getStudentId());
            preparedCreateStatement.setInt(2, registration.getSectionId());
            preparedCreateStatement.executeUpdate();

            return retrieve(registration.getStudentId());
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Registration retrieve(String studentId) {
        try {
            preparedRetrieveByIdStatement.setString(1, studentId);

            ResultSet resultSet = preparedRetrieveByIdStatement.executeQuery();

            if (resultSet.next()) {
                Registration registration = new Registration(resultSet.getString("studentId"), resultSet.getInt("sectionId"));
                return registration;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Registration> retrieve() {
        List<Registration> registrationList = new ArrayList();
        try {
            ResultSet resultSet = preparedRetrieveAllStatement.executeQuery();
            while (resultSet.next()) {
                Registration registration = new Registration(resultSet.getString("studentId"), resultSet.getInt("sectionId"));
                registrationList.add(registration);
            }
            return registrationList;
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Registration> retrieve(Predicate<Registration> predicate) {
        return retrieve().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
