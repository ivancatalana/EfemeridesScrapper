package org.example;

import java.util.LinkedList;
import java.util.List;

public class DiaDelAño {
    private int dia;
    private int mes;
    List<Nacimiento> nacimientoList= new LinkedList<>();
    List<Defuncion> defuncionList= new LinkedList<>();
    List<Acontecimiento> acontecimientoList= new LinkedList<>();

    public DiaDelAño(int dia, int mes, List<Nacimiento> nacimientoList, List<Defuncion> defuncionList, List<Acontecimiento> acontecimientoList){
        this.dia=dia;
        this.mes=mes;
        this.nacimientoList=nacimientoList;
        this.defuncionList=defuncionList;
        this.acontecimientoList=acontecimientoList;

    }

}
