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
                3) HQL ->Core javayı sql icine gömmek

        */

        // !!! 1.Yol : get() ***********************************************
        //Gelen yapı SQL tarzında, ama get()'e verdiğimiz ilk parametreyle gelen record Student01 data türüne döndürür.
//       Student01 student1 = session.get(Student01.class, 1001);
//       Student01 student2 = session.get(Student01.class, 1002);
//       Student01 student3 = session.get(Student01.class, 1003);
//
//       System.out.println(student1);
//       System.out.println(student2);
//       System.out.println(student3);

       //get()'i kaç kere cagırırsak o kadar DB ye gidip SELECT sorgusu calıstırır.
        // Bir kere de yapılacak işi kaç kere get() cagırdıysak o kadar yapar.
        //Bu da ekstra efora neden olur.

//        Student01{id=1001, name='Akin Toprak', grade=90}
//        Student01{id=1002, name='Cemil Bey', grade=90}
//        Student01{id=1003, name='Fatih Deniz', grade=90}

        // !!!  2.Yol : SQL *************************************************

        //String sqlQuery = "SELECT * FROM t_student01";

        //getResultList() recordları objeye cevirir(Javanın anlayacagı şekle).
        // List yapısında objeler getirir.Bu objelerin de fieldları geleceği icin array yapıda gelir.
        //Gelen her datayı alan Object[] datasını kullandık.
//        List<Object[]> resultList = session.createSQLQuery(sqlQuery).getResultList();
//        for (Object[] object: resultList) {
//            System.out.println(Arrays.toString(object));
//        }

        // !!! 3.Yol : HQL ***************************************************
            // Trick : HQL sorgusunda From dan sonra  javadaki class ismi kullanilmali
        String hqlQuery = "FROM Student01";
        List<Student01> resultList2 = session.createQuery(hqlQuery, Student01.class).getResultList();
        for (Student01 student01 : resultList2) {
            System.out.println(student01);
        }

        // Not : yukardaki 3 metodu kiyaslayalim ...
            // 1.hazir Metod 2. HQL 3. SQL
        // !!! Donecek kaydin unique ( tek bir tane ) oldugundan emin isem uniqueResult() ..
        // SQL ile
//        String sqlQuery2 = "SELECT * FROM t_student01 WHERE student_name ='Cemil Bey'";
//        Object[] uniqueResult1 = (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
//        System.out.println(Arrays.toString(uniqueResult1));
//
//        // yukarda 1 obje gelecek ama icinde fieldlar oldugu icin bunlarti ayri ayri almak istersem:
//        System.out.println(uniqueResult1[0] + " : " + uniqueResult1[1] + " : " + uniqueResult1[2]);
        // HQL ile
//        String hqlQuery2 = "FROM Student01 WHERE name='Cemil Bey'";
//        Student01 uniqueResult2 = session.createQuery(hqlQuery2, Student01.class).uniqueResult();
//        System.out.println(uniqueResult2);


        tx.commit();

       session.close();
       sf.close();
    }
}
