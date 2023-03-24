package com.hb02.embeddable;

public class RunnerSave02 {


    public static void main(String[] args) {


        Student02 student1 = new Student02();
        student1.setId(1001);
        student1.setName("Ebubekir");
        student1.setGrade(75);

        Address address1 = new Address();
        address1.setStreet("Apple Street");
        address1.setCity("NewYork");
        address1.setCountry("US");
        address1.setZipCode("67543");

        student1.setAddress(address1);


        Student02 student2 = new Student02();
        student1.setId(1002);
        student1.setName("Orhan Dmeir");
        student1.setGrade(75);

        Address address2 = new Address();
        address1.setStreet("Orange Street");
        address1.setCity("London");
        address1.setCountry("England");
        address1.setZipCode("06543");





    }
}
