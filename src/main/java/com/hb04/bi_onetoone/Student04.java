package com.hb04.bi_onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student04 {

    @Id
    private int id;

    @Column(name="std_name")
    private String name ;

    private int grade;

    //ilişkiyi kuracak taraf student fielda sahip class.
    //DBYE gitmeden kendi icinde hibernate mappedBy sayesinde classlar arası iletisimi saglıyor.
    @OneToOne(mappedBy = "student") //!!! "student" variable nerdeyse ben orda join column yaptım diyoruz.
   //mappedBY bize classlar arasında gezintiyi sağlar.Bunu yazmazsak DB de column olusturur. Biz onu istemiyoruz.
    //Classlardan classlara fieldlara ulasmak istiyoruz.
    private Diary04 diary;

    //!!! Getter- Setter

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

    public Diary04 getDiary() {
        return diary;
    }

    public void setDiary(Diary04 diary) {
        this.diary = diary;
    }

    // !!! toString()

    @Override
    public String toString() {
        return "Student04{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", diary=" + diary +
                '}';
    }
}
