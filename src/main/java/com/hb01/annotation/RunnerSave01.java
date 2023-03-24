package com.hb01.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave01 {

    public static void main(String[] args) {

        Student01 student1 = new Student01();
        student1.setId(1001);
        student1.setName("Akin Toprak");
        student1.setGrade(90);

        Student01 student2 = new Student01();
        student2.setId(1002);
        student2.setName("Cemil Bey");
        student2.setGrade(90);

        Student01 student3 = new Student01();
        student3.setId(1003);
        student3.setName("Fatih Deniz");
        student3.setGrade(90);


        //Path tanımlıyoruz gibi DB ile iletişime geçmek icin bir Session olusturuyoruz.
        //Aşağıdaki satırda Hibernate'e configuration dosyami ve Entity classimi bildiriyorum.
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class); //->Entity classımızı belirtiyoruz.

        //Session olusturarak DB ve Hibernate arasında köprü olusturuyoruz.
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession(); //->SessionFactory'den bana bir session aç demiş olduk.
        Transaction tx = session.beginTransaction(); //->Transaction ile DB ile iletişime geçip CRUD yapmamızı saglıycak.

       // session.save(student1);
        session.save(student2);
        session.save(student3);


        tx.commit(); //->Yapacağımız işlere onay veriyoruz.
        session.close();
        sf.close();


    }
}