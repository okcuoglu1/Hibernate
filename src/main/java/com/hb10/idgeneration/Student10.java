package com.hb10.idgeneration;

import javax.persistence.*;

@Entity
public class Student10 {

    /*
   Oracle DB - PostgreSQL kullanır ---> Sequence ( kontrolü developera bırakır, Id üretilirken
            başlangıç değeri veya kaç tane id cachelenecek bu gibi bilgileri developer setliyebilir)
   MySQL - Microsoft SQL kullanır   ---> IDENTITY ( kontrol DB de , kendi yapısına göre ıd oluşturur,
            içlerindeki en basitidir) -->Kullanımı en yaygın standart da 1 den baslar devam eder.

   GenerationType.AUTO ---> Hibernate otomatik olarak kullanilan DB ye gore stratejiyi belirler
   GenerationType.TABLE ---> En yavaşı , oyüzden çok kullanılmıyor, çünkü id
            üretmek için ekstra tablo oluşturuyor
 */


    //Silinen id ye geri dönemeyiz bir sonrakini alır mesela. 1-2-3-4(silindi)-5(yeni record idsi) --> 4e atamıyor.
    @GeneratedValue(generator = "sequence" , strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="sequence", // @GeneratedValue nun generator parametresi ile ayni isim olmali
            sequenceName = "student_seq", // DB de olusacak sequance ismi
            initialValue = 1000, // id lerim 1000 ile baslasin
            allocationSize = 10) // cache leme mekanizmasinda 10 adet id hazirda beklesin.


    @Id
    private int id;

    @Column(name = "student_name", nullable = false)
    private String name;

    private int grade;

    // !!! GETTER-sETTER

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    // !!! ToString()

    @Override
    public String toString() {
        return "Student10{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}