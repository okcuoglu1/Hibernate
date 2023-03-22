package com.hb01.annotation;

import javax.persistence.*;

@Entity //@Entity ile Bu sınıfın DB de bir tabloya karşılık gelmesini sağlıyoruz. student01
@Table(name="t_student01") //Eğer tablo ismini customize @Table annotation kullanırız.
                          //DB de tablo isminin "t_student01" olarak değişmesini sağladı

//Java kodu içinde bu class'a ulaşırken Student01 ile, SQL ile ulaşirken t_student01 ile yazmam lazım.
public class Student01 {

    //@Entity annotation koyuyorsak bu fieldlardan bir tanesi primary key olur.
    @Id //->@Id annotation'u columnu primary key yapar.
    //@Column(name="std_id") -> Column ismini customize eder.
    private int id;

    @Column(name="student_name", length=100, nullable=false, unique = false) //->Bazı kısıtlamalar getiriyoruz. Özelleştiriyoruz.
    private String name;

    //@Transient //->@Transient DB'deki tabloda grade isminde bir column oluşmasını engeller.
    //Bazı fieldların DB ile iletişimi olmasın isteyebiliriz. O zaman @Transient kullanırız.
    private int grade;

//    @Lob //->@Lob large object ile buyuk boyutlu data gelecek diyoruz.
//    Hibernate'e large object diye bildiriyoruz.
//    private byte[] image; -> image gibi yapılar diğerlerine kıyasla büyük mb kaplarlar o yüzden @Lob kullanırız.



        // !!! GETTER - SETTER

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        // !!! toString()

    @Override
    public String toString() {
        return "Student01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }


}
