/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

/**
 * @author HP
 */
public class Registration {
    private String studentId;
    private int sectionId;

    public Registration(String studentId, int sectionId) {
        this.studentId = studentId;
        this.sectionId = sectionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return "Registration{" + "studentId=" + studentId + ", sectionId=" + sectionId + '}';
    }

}
