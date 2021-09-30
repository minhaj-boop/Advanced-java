/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

/**
 * @author HP
 */
public class Faculty {
    private String initials;
    private String name;
    private String position;

    public Faculty() {
    }

    public Faculty(String initials, String name, String position) {
        this.initials = initials;
        this.name = name;
        this.position = position;
    }

    public String getInitials() {
        return initials;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Faculty{" + "initials=" + initials + ", name=" + name + ", position=" + position + '}';
    }

}
