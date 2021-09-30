/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foreignstep.mavenlombokdbdemo;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author HP
 */
public class Main {

    public Main() {
        System.out.println("Hello World");

        Student student = new Student(997l, "John Coe", LocalDate.of(2000, Month.JULY, 1));
        System.out.println(student);

        Connection connection = ConnectionSingleton.getConnection();
    }

    public static void main(String args[]) {
        new Main();
    }
}
