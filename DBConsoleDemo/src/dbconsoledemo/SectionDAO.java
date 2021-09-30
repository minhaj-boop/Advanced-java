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
public interface SectionDAO {
    Section create(Section section);

    Section retrieve(int sectionId);

    List<Section> retrieve();

    List<Section> retrieve(Predicate<Section> predicate);

    default int count() {
        return retrieve().size();
    }

}
