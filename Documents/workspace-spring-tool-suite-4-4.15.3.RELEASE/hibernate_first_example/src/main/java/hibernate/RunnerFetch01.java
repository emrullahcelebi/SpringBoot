package hibernate;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;



public class RunnerFetch01 {

public static void main(String[] args) {

Employee employee= new Employee();

Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class);

SessionFactory sf = con.buildSessionFactory();

Session session = sf.openSession();

Transaction tx = session.beginTransaction();



employee= session.get(Employee.class, 1001);

System.out.println(employee);



tx.commit();

session.close();

sf.close();

}

}