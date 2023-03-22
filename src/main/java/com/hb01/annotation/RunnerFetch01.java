package com.hb01.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch01 {

    public static void main(String[] args) {

        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
       /*
            DB den bilgi almak istiyorsam
                1) get()
                2) SQL
                3) HQL

        */

        // !!! 1.Yol : get() ***********************************************
        Student01 student1 = session.get(Student01.class, 1001);
        Student01 student2 = session.get(Student01.class, 1002);
        Student01 student3 = session.get(Student01.class, 1003);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

        // !!!  2.Yol : SQL *************************************************

        String sqlQuery = "SELECT * FROM t_student01";
        List<Object[]> resultList = session.createSQLQuery(sqlQuery).getResultList();
        for (Object[] object: resultList) {
            System.out.println(Arrays.toString(object));
        }





        tx.commit();

        session.close();
        sf.close();
    }
}