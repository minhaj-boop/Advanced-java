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
public interface RegistrationDAO {
    Registration create(Registration registration);

    Registration retrieve(String studentId);

    List<Registration> retrieve();

    List<Registration> retrieve(Predicate<Registration> predicate);

    default int count() {
        return retrieve().size();
    }

}
