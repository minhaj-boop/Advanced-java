/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foreignstep.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * @author HP
 */
public abstract class CrudDaoMySQLImplementation<T, I> implements CrudDao<T, I> {

    @Override
    public void create(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> retrieve() {
        Session session = null;
        T t = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            //TODO ensure that you are using a generic query
            String classname = t.getClass().getSimpleName();
            System.out.printf("Class name [%s]\n", classname);
            Query<?> createQuery = session.createQuery("from " + classname, t.getClass());
            return (List<T>) createQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public T retrieve(I id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
