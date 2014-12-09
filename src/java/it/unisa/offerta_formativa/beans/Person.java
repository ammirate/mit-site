/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.beans;

/**
 *
 * @author Antonio
 */
public class Person {

    private String SSN;
    private String name;
    private String surname;
    private String phone;
    private String city;
    private String address;
    private String zipCode;
    private String cityzenship;
    private String gender;
    private String accountEmail;
    private String departmentAbbreviation;
    private String webPage;
    private String university;
    private String matricula;
    private String position;
    private int cyle;

    public Person(String ssn) {
        this.SSN = ssn;
    }

    public Person(String ssn, String accountEmail,String name, String surname) {
        this.SSN = ssn;
        this.accountEmail = accountEmail;
        this.name=name;
        this.surname=surname;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityzenship() {
        return cityzenship;
    }

    public void setCityzenship(String cityzenship) {
        this.cityzenship = cityzenship;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getDepartmentAbbreviation() {
        return departmentAbbreviation;
    }

    public void setDepartmentAbbreviation(String departmentAbbreviation) {
        this.departmentAbbreviation = departmentAbbreviation;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCyle() {
        return cyle;
    }

    public void setCyle(int cyle) {
        this.cyle = cyle;
    }

}
