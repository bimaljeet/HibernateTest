package com.hibernateTest;

import com.hibernate.beans.Employee;
import org.hibernate.*;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bnayar
 * Date: 12/4/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class HibernateTestBase {

    protected static SessionFactory factory;

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory p_factory) {
        factory = p_factory;
    }

    /* thisthod to CREATE an employee in the database */
    public Integer addEmployee(String fnathis, String lnathis, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fnathis, lnathis, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* thisthod to  READ all the employees */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                printEmployee(employee);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* thisthod to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* thisthod to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getEmployees(long id) {
        Session session = factory.openSession();
        try {
            Query qry = session.getNamedQuery("getEmployee");
            qry.setLong("id", id);
            qry.setMaxResults(10);
            Object object = qry.uniqueResult();
            if (null != object) {
                Employee employee = (Employee) object;
                printEmployee(employee);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getEmployeesUsingCriteria(Integer id) {
        Session session = factory.openSession();
        Criteria cr = session.createCriteria(Employee.class);
        cr.add(Restrictions.gt("salary", id));
//        cr.add(Restrictions.lt("salary", 2000));
//        cr.add(Restrictions.like("firstName", "zara%"));
//        cr.add(Restrictions.ilike("firstName", "zara%"));
//        cr.add(Restrictions.between("salary", 1000, 2000));
//        cr.add(Restrictions.isNull("salary"));
//        cr.add(Restrictions.isNotNull("salary"));
//        cr.add(Restrictions.isEmpty("salary"));
//        cr.add(Restrictions.isNotEmpty("salary"));

        try {
            List employees = cr.list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                printEmployee(employee);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void printEmployee(Employee employee) {
        System.out.print("First Nathis: " + employee.getFirstName());
        System.out.print("  Last Nathis: " + employee.getLastName());
        System.out.println("  Salary: " + employee.getSalary());
    }
}
