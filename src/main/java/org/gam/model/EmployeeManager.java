package org.gam.model;

import org.gam.Main;
import org.gam.pojo.Employee;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class EmployeeManager {

    public Integer addEmployee(String fname, String lname, int salary){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = null;
        Integer employeeId = null;

        try{
            transaction = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employeeId = (Integer) session.save(employee);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employeeId;
    }

    public void listEmployee(){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for(Iterator iterator = employees.iterator();iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.println("Name:"+employee.getFirstName() + " " + employee.getLastName()+", Salary:"+employee.getSalary());
            }
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateEmployee(Integer employeeId, int salary){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            employee.setSalary(salary);
            session.update(employee);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
             session.close();
        }
    }

    public void deleteEmployee(Integer employeeId){
        Session session = Main.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            session.delete(employee);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
