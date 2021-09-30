/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author HP
 */
public interface FacultyDAO {
    Faculty create(Faculty faculty);

    Faculty retrieve(String facultyInitials);

    List<Faculty> retrieve();

    List<Faculty> retrieve(Predicate<Faculty> predicate);

    default int count() {
        return retrieve().size();
    }
}
