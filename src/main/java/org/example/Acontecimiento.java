package org.example;

public class Acontecimiento {
    private int year;
    private String descripcion;

    public Acontecimiento(int year , String descripcion){
        this.year=year;
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

}

