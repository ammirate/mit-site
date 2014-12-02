package it.unisa.offerta_formativa.beans;

import it.unisa.tp.model.interfaces.Account;


/**
 *
 * @author Antonio
 */
public class Professor extends Person{
    
    private String position;
    private String phone;
    private String officeHours;
    private String email;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    private Account account;
    

    public Professor(Account account) {
        this.account = account;
    }
    
    
    
}
