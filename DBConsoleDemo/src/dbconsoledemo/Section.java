/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

/**
 * @author HP
 */
public class Section {
    private int id;
    private int sectionNumber;
    private int semester;
    private int seatLimit;
    private String code;
    private String initials;

    public Section(int id, int sectionNumber, int semester, int seatLimit, String code, String initials) {
        this.id = id;
        this.sectionNumber = sectionNumber;
        this.semester = semester;
        this.seatLimit = seatLimit;
        this.code = code;
        this.initials = initials;
    }

    public int getId() {
        return id;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public int getSemester() {
        return semester;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public String getCode() {
        return code;
    }

    public String getInitials() {
        return initials;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public String toString() {
        return "Section{" + "id=" + id + ", sectionNumber=" + sectionNumber + ", semester=" + semester + ", seatLimit=" + seatLimit + ", code=" + code + ", initials=" + initials + '}';
    }
}
