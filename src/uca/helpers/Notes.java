/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.helpers;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Notes {
    String module_name;
    Float notes;
    String validation;
    String add_date;
    
    
    public Notes(String module_name, Float notes, String validation, String add_date) {
        this.module_name = module_name;
        this.notes = notes;
        this.validation = validation;
        this.add_date = add_date;
    }
    
    
    public String getModule_name() {
        return module_name;
    }

    public Float getNotes() {
        return notes;
    }

    public String getValidation() {
        return validation;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public void setNotes(Float notes) {
        this.notes = notes;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }
    
       

    
}
