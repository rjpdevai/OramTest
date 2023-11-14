package org.gam;

import org.gam.model.EmployeeManager;
import org.gam.pojo.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static SessionFactory sessionFactory;
    public static void main(String[] args) {
        try{
            sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Failed to create sessionFactory object."+ex);
            throw new ExceptionInInitializerError(ex);
        }

        EmployeeManager employeeManager = new EmployeeManager();
//        employeeManager.addEmployee("Daisy","Das", 13000);
//        employeeManager.addEmployee("Ross","Chen", 7000);
        //employeeManager.updateEmployee(1,5000);
        employeeManager.deleteEmployee(4);
        employeeManager.listEmployee();

    }
}