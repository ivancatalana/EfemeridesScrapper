package org.example;

import java.time.LocalDate;
import java.util.Date;

public class Acontecimiento {
    private int year;
    private LocalDate date;
    private String descripcion;

    public Acontecimiento(int year , LocalDate date, String descripcion){
        this.year=year;
        this.date=date;
        this.descripcion=descripcion;


    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}

