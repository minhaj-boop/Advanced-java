package com.foreignstep.coursemanagementserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"code"})
public class Course {
    private String code;
    private String title;
    //@ToString.Exclude
    private List<Section> sectionList;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public void addSection(Section section){
        if(sectionList == null)
            sectionList = new ArrayList<>();
        sectionList.add(section);
    }
}
