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
public interface CourseDAO {
    Course create(Course course);

    Course retrieve(String courseCode);

    List<Course> retrieve();

    List<Course> retrieve(Predicate<Course> predicate);

    default int count() {
        return retrieve().size();
    }
}
