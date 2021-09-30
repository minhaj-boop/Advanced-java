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
public interface StudentDAO {
    Student create(Student student);

    Student retrieve(String studentId);

    List<Student> retrieve();

    List<Student> retrieve(Predicate<Student> predicate);

    default int count() {
        return retrieve().size();
    }

    int deletAll();
}
