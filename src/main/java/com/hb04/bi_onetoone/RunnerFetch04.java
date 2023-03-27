package com.hb04.bi_onetoone;

import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch04 {

    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").
                                                 addAnnotatedClass(Diary04.class).
                                                 addAnnotatedClass(Student04.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        //belli id li student getirelim
        Student04 student = session.get(Student04.class, 1001);

        //diary ile getirelim
       Diary04 diary =  session.get(Diary04.class, 101);


        System.out.println(student);
        System.out.println("******************");
        System.out.println(diary);
        System.out.println("*****************");
        System.out.println("Diary üzerinden student objesine ulasalım : " + diary.getStudent());
        System.out.println("****************");
        System.out.println("Student üzerinden diary objesine ulasalım : " + student.getDiary());

        //Task: Kesisim kumesini bana getirin (inner join)
        String hqlQuery = "SELECT s.name, d.name FROM Student04 s INNER JOIN FETCH Diary04 d on s.id=d.student";

        //İki farklı classdan data geldiği icin object kullanmak zorunda kaldık.
       List<Object[]> resultList =  session.createQuery(hqlQuery).getResultList();
//       for(Object[] object : resultList){
//           System.out.println(Arrays.toString(object));
//       }

        //Lambda expression ile yazalım
        //stream'e sokmaya gerek yok list yapısı buna müsade ediyor.
        resultList.forEach(oa-> {
            System.out.println(Arrays.toString(oa));
        });

        //Task2; HQL ile left join
        String hqlQuery2 = "SELECT * FROM Student04 LEFT JOIN FETCH Diary04 d on s.id=d.student ";
        List<Object[]> resultList2 =  session.createQuery(hqlQuery2).getResultList();
        resultList2.forEach(oa ->{
            System.out.println(Arrays.toString(oa));
        });

        // !!! HQL Right Join
       // Task 3 : Butun gunlukler ve varsa gunlugu olan ogrenciler gelsin
        String hqlQuery3 = "SELECT s.name, d.name FROM Student04 s RIGHT JOIN FETCH Diary04 d on s.id = d.student";
        List<Object[]> resultList3 =  session.createQuery(hqlQuery3).getResultList();
        resultList3.forEach( oa -> {
            System.out.println(Arrays.toString(oa));
        });

        // !!! HQL Full Join **************************
        // Kisaca : butun student ve diary bilgilerini getirelim
        String hqlQuery4 = "SELECT s.name, d.name FROM Student04 s FULL JOIN FETCH Diary04 d on s.id=d.student.id";
        List<Object[]> resultList4 = session.createQuery(hqlQuery4).getResultList();
        resultList4.forEach( oa -> {
            System.out.println(Arrays.toString(oa));
        });


        tx.commit();
        sf.close();
        session.close();




    }
}
