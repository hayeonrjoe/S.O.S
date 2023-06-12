package com.sos.signal.admin;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Integer aId;

    @Column(name = "a_name", length = 10, nullable = false)
    private String a_name;

    @Column(name = "a_contact", length = 30, nullable = false)
    private String a_contact;

    @Column(name = "a_email", length = 50, nullable = false)
    private String aEmail;

    @Column(name = "a_pw", length = 10, nullable = false)
    private String aPw;

    @Column(name = "a_admin_type")
    private String adminType;

    @Column(name = "a_company_name", length = 50, nullable = false)
    private String a_company_name;

    @Column(name = "a_company_contact", length = 30, nullable = false)
    private String a_company_contact;

    private String a_company_address;

    public Admin() {

    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_contact() {
        return a_contact;
    }

    public void setA_contact(String a_contact) {
        this.a_contact = a_contact;
    }

    public String getAEmail() {
        return aEmail;
    }

    public void setAEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getAPw() {
        return aPw;
    }

    public void setAPw(String aPw) {
        this.aPw = aPw;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String a_admin_type) {
        this.adminType = a_admin_type;
    }

    public String getA_company_name() {
        return a_company_name;
    }

    public void setA_company_name(String a_company_name) {
        this.a_company_name = a_company_name;
    }

    public String getA_company_contact() {
        return a_company_contact;
    }

    public void setA_company_contact(String a_company_contact) {
        this.a_company_contact = a_company_contact;
    }

    public String getA_company_address() {
        return a_company_address;
    }

    public void setA_company_address(String a_company_address) {
        this.a_company_address = a_company_address;
    }

    public boolean checkPassword(String aPw) {
        return this.aPw.equals(aPw);
    }
}