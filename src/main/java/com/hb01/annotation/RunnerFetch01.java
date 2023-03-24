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
        //Mappleme yaptık. Gelen record Student01 data türünde olacak dedik.
//        Student01 student1 = session.get(Student01.class, 1001);
//        Student01 student2 = session.get(Student01.class, 1002);
//        Student01 student3 = session.get(Student01.class, 1003);
//        System.out.println(student1);
//        System.out.println(student2);
//        System.out.println(student3);

       //get()'i kaç kere cagırırsak o kadar DB ye gidip SELECT sorgusu calıstırır.
        // Bir kere de yapılacak işi kaç kere get() cagırdıysak o kadar yapar.
        //Bu da ekstra efora neden olur.

//        Student01{id=1001, name='Akin Toprak', grade=90}
//        Student01{id=1002, name='Cemil Bey', grade=90}
//        Student01{id=1003, name='Fatih Deniz', grade=90}

        // !!!  2.Yol : SQL *************************************************

//        String sqlQuery = "SELECT * FROM t_student01";

        //getResultList() birden fazla data geleceğini ve recordları da core Java'nın anlayacağı şekilde list yapısına atar.
        // List yapısında objeler getirir.Bu objelerin de fieldları geleceği icin array yapıda gelir.
        //Gelen her datayı alan Object[] datasını kullandık.
//        List<Object[]> resultList = session.createSQLQuery(sqlQuery).getResultList();
//        for (Object[] object: resultList) {
//            System.out.println(Arrays.toString(object));
//        }

        // !!! 3.Yol : HQL ***************************************************
            // Trick : HQL sorgusunda From dan sonra  javadaki class ismi kullanilmali
  //        String hqlQuery = "FROM Student01";
        //createQuery(hqlQuery, Student01.class) ->Önce queryimi gönderiyoruz sonra mappliyeceği classı belirtiyoruz.
        //HQL de data türünü mapplediğimiz icin object data type ile ugrasmamıza gerek yok.
//          List<Student01> resultList2 = session.createQuery(hqlQuery, Student01.class).getResultList();
//          for (Student01 student01 : resultList2) {
//              System.out.println(student01);
//          }

        // Not : yukardaki 3 metodu kiyaslayalim ...
            //KULLANIM SIRASI BEST PRACTICE ->> 1.hazir Metod 2. HQL 3. SQL
        /*
        KULLANIM SIRASI NEDEN?
       1) 3'ü de aynı şeyi karşılıyorsa hazır methodları kullanırız. Çünkü SQL ve HQL Stringlerle çalışır ve hataya açıktır.
       2) Hazır method yoksa 2.seçenek HQL. Çünkü projenin ilerki zamanlarında native sql türü değişebilir(msSQL,OracleSQL vb.)
       HQL bu değişimlere hazırdır ve kod patlamaz. Ama biz postgreSQL ile yazdıgımız sorgu sonra update edildiğinde farklı bir
       türe geçtiğimizde kod patlar.
         */

        //----------------------------------------------------------------

        //Getireceğimiz data tek bir dataysa mesela id ile sorgu sağlayacaksak... getResultList'e gerek yok!
        // !!! Donecek kaydin unique ( tek bir tane ) oldugundan emin isem uniqueResult() ..

        // SQL ile
        //mappleme yapamadığımız için object[] ile aldık.
        //Gelen recordun hangi data typelarında olacağını bilemediğimiz için hata almamak için kendimizi garantiye alıyoruz. Object[] kullanıyoruz.
//        String sqlQuery2 = "SELECT * FROM t_student01 WHERE student_name ='Cemil Bey'";
//        Object[] uniqueResult1 = (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
//        System.out.println(Arrays.toString(uniqueResult1));

        //Array almamızın bir diğer yararı da; icindeki fiedları ayrı ayrı almak istersek index verip alabiliriz.
        // yukarda 1 obje gelecek ama icinde fieldlar oldugu icin bunlari ayri ayri almak istersem:
//        System.out.println(uniqueResult1[0] + " : " + uniqueResult1[1] + " : " + uniqueResult1[2]);


     // HQL ile
     //mappleme yapabildik. Gelen recordun data typeını bildiğimiz icin object e falan gerek yok. HQL avantajı.
//       String hqlQuery2 = "FROM Student01 WHERE name='Cemil Bey'";
//       Student01 uniqueResult2 = session.createQuery(hqlQuery2, Student01.class).uniqueResult();
//       System.out.println(uniqueResult2);


       //!!!YUKARDAKI sorguyu HQL alias(takma ad) kullanarak yapalım
//        String hqlQuery3 = "FROM Student01 as std  WHERE std.name='Cemil Bey'";
//        Student01 uniqueResult3 = session.createQuery(hqlQuery3, Student01.class).uniqueResult();
//        System.out.println(uniqueResult3);

        //!! HQL ile grade değeri 90 olan ogrencileri getirelim-
        String hqlQuery4 = "SELECT s.id, s.name FROM Student01 s WHERE s.grade=90";
        List<Object[]> resultList4 = session.createQuery(hqlQuery4).getResultList();
        for(Object[] object : resultList4){
            System.out.println(Arrays.toString(object));
        }



        tx.commit();

       session.close();
       sf.close();
    }
}
