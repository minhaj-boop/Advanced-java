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
public class SectionDAOMySQLImplementation implements SectionDAO {

    Connection connection;
    private PreparedStatement preparedCreatedStatement;
    private PreparedStatement preparedRetrieveByIdStatement;
    private PreparedStatement preparedRetrieveAllStatement;

    public SectionDAOMySQLImplementation() {
        try {
            connection = DBConnection.getConnection();

            InputStream inputStream = getClass().getResourceAsStream("resources/query.properties");
            InputStreamReader fileReader = new InputStreamReader(inputStream);

            Properties properties = new Properties();
            properties.load(fileReader);

            preparedCreatedStatement = connection.prepareStatement(properties.getProperty("INSERT_SECTION_QUERY"));
            preparedRetrieveByIdStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_SECTION_BY_ID_QUERY"));
            preparedRetrieveAllStatement = connection.prepareStatement(properties.getProperty("RETRIEVE_ALL_SECTION_QUERY"));
        } catch (IOException ex) {
            Logger.getLogger(SectionDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Section create(Section section) {
        try {
            preparedCreatedStatement.setInt(1, section.getId());
            preparedCreatedStatement.setInt(2, section.getSectionNumber());
            preparedCreatedStatement.setInt(3, section.getSemester());
            preparedCreatedStatement.setInt(4, section.getSeatLimit());
            preparedCreatedStatement.setString(5, section.getCode());
            preparedCreatedStatement.setString(6, section.getInitials());
            preparedCreatedStatement.executeUpdate();

            return retrieve(section.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Section retrieve(int sectionId) {
        try {
            preparedRetrieveByIdStatement.setInt(1, sectionId);
            ResultSet resultSet = preparedRetrieveByIdStatement.executeQuery();

            if (resultSet.next()) {
                Section section = new Section(resultSet.getInt("id"), resultSet.getInt("sectionNumber"), resultSet.getInt("semester"), resultSet.getInt("seatLimit"), resultSet.getString("code"), resultSet.getString("initials"));
                return section;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Section> retrieve() {
        List sectionList = new ArrayList();
        try {
            ResultSet resultSet = preparedRetrieveAllStatement.executeQuery();
            while (resultSet.next()) {
                Section section = new Section(resultSet.getInt("id"), resultSet.getInt("sectionNumber"), resultSet.getInt("semester"), resultSet.getInt("seatLimit"), resultSet.getString("code"), resultSet.getString("initials"));
                sectionList.add(section);
            }
            return sectionList;
        } catch (SQLException ex) {
            Logger.getLogger(SectionDAOMySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Section> retrieve(Predicate<Section> predicate) {
        return retrieve().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
