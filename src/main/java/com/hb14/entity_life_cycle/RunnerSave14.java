package com.hb14.entity_life_cycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave14 {

    public static void main(String[] args) {

        Student14 student1 = new Student14(); // !!! Transient
        student1.setName("Kasim");
        student1.setMathGrade(55);

        Student14 student2 = new Student14(); // !!! Transient
        student2.setName("Gulsen");
        student2.setMathGrade(65);

        Configuration con = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student14.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(student1); // !!! persistent state

        //Hibernate; Bir objeyi DB ye Save(persistent) ettikten sonra onu takibe alır ve yapılan
        //değişiklikleri takip edip kendisi save eder. Bir değişiklik yaptıktaın sonra save methoduna ihtiyac yoktur.
        //tx.commit(); methodunu gördüğünde DB de değişiklik yapar.
        student1.setName("hakki");


        //İşte her zaman takipte kalmasın performans düşüklüğü yasamasın diye detached state var.

        session.evict(student1); // detached state-> Bu objeyi takip etmeyi bırak.
        //bir objeyi detached edersek yukarda yaptığımız değişiklikler geçersiz olur.

        student1.setName("hakki"); // bu asamada obje life-cycle olarak hala detach asamasinda oldugu icin isim degisikligi gerceklesmez

        //update veya merge ı gördüğünde tekrar objeyi takibe alır.
        session.update(student1); // persistent scope
        session.merge(student1); // persistent scope


        tx.commit();
        session.close();
        sf.close();
    }
}