package com.hb11.criteriaapi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Random;

public class RunnerSave11 {

    public static void main(String[] args) {

        Configuration conf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student11.class);

        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        //20 tane Student objesi oluşturup persist(kalıcı) yapalım

        Random random = new Random();

        for(int i = 1; i<=20; i++) {

            Student11 student = new Student11();
            student.setName("Student Name " + i);
            student.setMathGrade(random.nextInt(101)); //100 dahil değil
            session.save(student);

        }




        tx.commit();
        session.close();
        sf.close();




    }


}
