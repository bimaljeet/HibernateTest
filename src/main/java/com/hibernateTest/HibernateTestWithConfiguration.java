package com.hibernateTest;

import com.hibernate.beans.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import java.util.Iterator;
import java.util.List;


public class HibernateTestWithConfiguration extends HibernateTestBase {

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void startTesting() {

      /* Add few employee records in database */
//        Integer empID1 = this.addEmployee("Zara", "Ali", 1000);
//        Integer empID2 = this.addEmployee("Daisy", "Das", 5000);
//        Integer empID3 = this.addEmployee("John", "Paul", 10000);

      /* List down all the employees */
        this.listEmployees();

      /* Update employee's records */
//        this.updateEmployee(empID1, 5000);

      /* Delete an employee from the database */
//        this.deleteEmployee(empID2);

      /* List down new list of the employees */
//        this.listEmployees();
    }


}
