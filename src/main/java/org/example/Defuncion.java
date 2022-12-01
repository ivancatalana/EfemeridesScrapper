package org.example;

public class Defuncion {
    private int yearNacim;
    private int yearMuerte;
    private String nombre;
    private String descripcion;

    public Defuncion(int yearMuerte, int yearNacim, String nombre, String descripcion){
        this.yearNacim=yearNacim;
        this.yearMuerte=yearMuerte;
        this.nombre=nombre;
        this.descripcion=descripcion;


    }
    public int getYearNacim() {
        return yearNacim;
    }

    public void setYearNacim(int yearNacim) {
        this.yearNacim = yearNacim;
    }

    public int getYearMuerte() {
        return yearMuerte;
    }

    public void setYearMuerte(int yearMuerte) {
        this.yearMuerte = yearMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

