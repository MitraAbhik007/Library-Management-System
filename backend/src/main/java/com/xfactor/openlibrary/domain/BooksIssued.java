package com.xfactor.openlibrary.domain;

import java.sql.Date;

import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import javax.persistence.Table;

public class BooksIssued {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String isbn;
    @Column
    private Long s_id;
    @Column
    private Date taken_date;
    @Column
    private Date return_date;

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Long getS_id() {
        return s_id;
    }
    public void setS_id(Long s_id) {
        this.s_id = s_id;
    }
    public Date getTaken_date() {
        return taken_date;
    }
    public void setTaken_date(Date taken_date) {
        this.taken_date = taken_date;
    }
    public Date getReturn_date() {
        return return_date;
    }
    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public BooksIssued(String isbn,Long s_id,Date taken_date,Date return_date) {
        this.isbn = isbn;
        this.s_id=s_id;
        this.taken_date=taken_date;
        this.return_date=return_date;   
    }
    
}
