package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapperEfemerides {
    FirefoxOptions options = new FirefoxOptions();
    WebDriver driver = new FirefoxDriver(options);
    String baseUrl = "";
    List<WebElement> tagNameElementsP;
//Creamos la lista de webelements ??
    List <List<WebElement>> webElementsList;




    //Funcion que nos encuentra todos los elementos p de la pagina
    public void getElementsP() {

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 1; j++) {


                baseUrl = "https://www.efemerides20.com/" + j + "-de-"+mesDelAño(i);
                driver.get(baseUrl);

                tagNameElementsP = new LinkedList<>(driver.findElements((By.tagName("p"))));
                System.out.println("Tamaño elementos P: "+tagNameElementsP.size());
                 getNacimiento();
               // getAcontecimiento();
    /*            for (WebElement n : tagNameElementsP) {
                    System.out.println(n.getText());

                }
     */

            }
        }
    }
    //Función que devuelve los acontecimientos dentro de un dia
    public void getAcontecimiento(){

//Patron para extraer el año
        Pattern yearAcontecimiento = Pattern.compile("(?<= de )([\\s\\S]\\d*?)(?= )");


        for (WebElement n : tagNameElementsP) {


            if (!n.getText().contains("nace") && !n.getText().contains("muere")) {
                Matcher yearAcontecimientoM = yearAcontecimiento.matcher(n.getText());
    //            Matcher descripcionAcontecimientoM = descripcionAcontecimiento.matcher(n.getText());
                if (yearAcontecimientoM.find()) {

                    try {


                        String year = "" + yearAcontecimientoM.group(0);
                        String[] yearSplit = n.getText().split("(\\d+)\\s(?!de)");
 /*
                        for (String s : yearSplit){
                            System.out.println(s);
                        }


  */
                        System.out.println("Acontecimiento "+year);

                        System.out.println("Acontecimiento con descripcion:  "+yearSplit[1]);

                    } catch (NumberFormatException e) {

                      }
                }
            }
        }

    }

    //Función que devuelve los Nacimientos dentro de un dia
    public void getNacimiento(){
        Pattern yearNacimiento = Pattern.compile("(?<= de )([\\s\\S]\\d*?)(?= nace )");
        Pattern EnteNacimiento = Pattern.compile("(?<=nace )([\\s\\S].*?)(?=,)");
        List<WebElement> acontecimientoList = new LinkedList<>();

        for (WebElement n : tagNameElementsP) {


            if (n.getText().contains("nace")) {
                Matcher yearNacimientoM = yearNacimiento.matcher(n.getText());
                Matcher enteNacimientoM = EnteNacimiento.matcher(n.getText());
                if (yearNacimientoM.find()) {

                    try {


                        String year = "" + yearNacimientoM.group(0);
                        String[] yearSplit = n.getText().split("(\\d+)\\s(?!de)");
                        for (String s : yearSplit){
                            System.out.println(s);

                        }
                        if (enteNacimientoM.find())   System.out.println(enteNacimientoM.group(0));


                        System.out.println("Nacimiento del año "+yearNacimientoM.group(0));

                    } catch (NumberFormatException e) {

                    }
                }
            }
        }


    }


    //Función que devuelve las defunciones dentro de un dia
    public void getDefunciones(String linea){


    }

    // Funcion que nos devuelve el mes para completar el webdriver
    public String mesDelAño(int j){
        String mes1="enero";
        String mes2="febrero";
        String mes3="marzo";
        String mes4="abril";
        String mes5="mayo";
        String mes6="junio";
        String mes7="julio";
        String mes8="agosto";
        String mes9="septiembre";
        String mes10="octubre";
        String mes11="noviembre";
        String mes12="diciembre";
        String mes="";

        switch(j) {
            case 1:

                mes = mes1;
                break;
            case 2:
                mes = mes2;
                break;
            case 3:
                mes = mes3;
                break;
            case 4:
                mes = mes4;
                break;
            case 5:
                mes = mes5;
                break;
            case 6:
                mes = mes6;
                break;
            case 7:
                mes = mes7;
                break;
            case 8:
                mes = mes8;
                break;
            case 9:
                mes = mes9;
                break;
            case 10:
                mes = mes10;
                break;
            case 11:
                mes = mes11;
                break;
            case 12:
                mes = mes12;
                break;
        }
        return mes;
    }
}