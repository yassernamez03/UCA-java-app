/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.helpers;

/**
 *
 * @author admin
 */
public class Student {
    public String cne ;
    public String first_name;
    public String last_name;
    public String birth_date;
    public String address ;

    public Student(String cne, String first_name, String last_name, String birth_date, String address) {
        this.cne = cne;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.address = address;
    }

}
