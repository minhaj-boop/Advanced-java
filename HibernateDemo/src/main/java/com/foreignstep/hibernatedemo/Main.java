/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foreignstep.hibernatedemo;

import org.hibernate.Session;

/**
 * @author HP
 */
public class Main {

    public Main() {
        System.out.println("Hello World!");

        Session openSession = HibernateUtil.getSessionFactory().openSession();

        System.out.println(openSession);
    }

    public static void main(String[] args) {
        new Main();
    }

}
