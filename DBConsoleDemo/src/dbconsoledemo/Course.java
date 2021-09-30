/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

/**
 * @author HP
 */
public class Course {
    private String code;
    private String title;
    private double credits;

    public Course(String code, String title, double credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public Course() {
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public double getCredits() {
        return credits;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" + "code=" + code + ", title=" + title + ", credits=" + credits + '}';
    }


}
