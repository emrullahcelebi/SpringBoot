package hibernate;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;



public class RunnerSave01 {

public static void main(String[] args) {

Employee employee1 = new Employee();

employee1.setId(1001);

employee1.setName("John Coffee");

employee1.setAge(34);


Employee employee2 = new Employee();

employee2.setId(1002);

employee2.setName("Tony Stark");

employee2.setAge(30);



Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class);

SessionFactory sf = con.buildSessionFactory();

Session session = sf.openSession();

Transaction tx = session.beginTransaction();



session.save(employee1);

session.save(employee2);



tx.commit();

session.close();

sf.close();

}

}