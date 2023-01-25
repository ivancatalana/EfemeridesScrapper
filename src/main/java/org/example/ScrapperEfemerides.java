package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.xml.sax.SAXException;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


        /**

         * Esta clase define metodos que conectarán con la web efemerides.com para extraer la información

         * @author: Ivan Morales Mirete

         * @version: 13/12/2022/A

         */


public class ScrapperEfemerides {
    //Campos de clase
    FirefoxOptions options = new FirefoxOptions();
    WebDriver driver = new FirefoxDriver(options);
    String baseUrl = "";
    List<WebElement> tagNameElementsP;

    //Creamos la lista de webelements ??
    List <List<WebElement>> webElementsList;

    List<Acontecimiento> acontecimientoList = new LinkedList<>();
    List<Nacimiento> nacimientoList = new LinkedList<>();
    List<Defuncion> defuncionList = new LinkedList<>();

    //Instanciamos nuestro creador de CSV
    CSVcreator csVcreator = new CSVcreator();
    XMLcreator xmLcreator = new XMLcreator();
    int contadorDias=0;

            public ScrapperEfemerides() throws IOException, SAXException {
            }

            /**

             * Método que devuelve el número de ítems (p) existentes en cada día que recorreremos dentro de la web

             * @baseUrl La dirección web a la que conectaremos para extraer sus elementos

             * @tagNameElementsP  La Lista de elementos a la cual pasaremos nuestros métodos para extraer la información en función del evento

             */
    //Funcion que nos encuentra todos los elementos p de la página
    public void getElementsP() {

        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 31; j++) {


                baseUrl = "https://www.efemerides20.com/" + j + "-de-"+mesDelAño(i);
                driver.get(baseUrl);

                tagNameElementsP = new LinkedList<>(driver.findElements((By.tagName("p"))));
                System.out.println("Tamaño elementos P: "+tagNameElementsP.size());
                 getNacimiento(j,i);
                 getDefunciones(j,i);
                 getAcontecimiento(j,i);
                 contadorDias++;
                  if (i==i+1) System.out.println(contadorDias+"");
            }
        }

    }

            /**

             * Funcion que devuelve los acontecimientos dentro de un dia

             * @param dia  el dia del año
             * @param mes  el mes del año

             */
    //Función que devuelve los acontecimientos dentro de un dia
    public void getAcontecimiento(int dia , int mes){

                //Creamos Acontecimiento para escribir CSV

        Acontecimiento acontecimiento = new Acontecimiento(0,LocalDate.now(),"");

               //Patron para extraer el año
        Pattern yearAcontecimiento = Pattern.compile("(?<= de )([\\s\\S]\\d*?)(?= )");


        for (WebElement n : tagNameElementsP) {


            if (!n.getText().contains("nace") && !n.getText().contains("muere")) {
                Matcher yearAcontecimientoM = yearAcontecimiento.matcher(n.getText());
                if (yearAcontecimientoM.find()) {

                    try {


                        String year = "" + yearAcontecimientoM.group(0);
                        String[] yearSplit = n.getText().split("(\\d+)\\s(?!de)");
                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                        String stringFecha = ""+year+"-"+mes+"-"+dia;
                        Date fecha= sdf.parse(stringFecha);
                        LocalDate fechaSinHora= LocalDate.parse(sdf.format(fecha));

                        try {
                            if (yearSplit.length!=0) {
                                String[] acontecimientoString = {stringFecha, year, yearSplit[1]};

                                csVcreator.escribirArchivoAcontecimiento(acontecimientoString);
                                xmLcreator.escribirArchivoAcontecimiento(acontecimientoString);
                            }
                            } catch (ArrayIndexOutOfBoundsException e)   {
                            System.out.println("Error out of bounds en Acontecimieno del " +stringFecha);

                            }

                    } catch (NumberFormatException e) {

                      } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
            /**

             * Funcion que devuelve los Nacimientos de un dia

             * @param dia  el dia del año
             * @param mes  el mes del año

             */
    //Función que devuelve los Nacimientos dentro de un dia
    public void getNacimiento(int dia, int mes){
        int diaD=dia;
        int mesD= mes;
        String yearDefuncion="???";
        String descriptionClean="";
        String[]description;
        Pattern yearNacimiento = Pattern.compile("(?<= de )([\\s\\S]\\d*?)(?= nace )");
        Pattern EnteNacimiento = Pattern.compile("(?<=nace )([\\s\\S].*?)(?=,)");
        List<WebElement> acontecimientoList = new LinkedList<>();

        for (WebElement n : tagNameElementsP) {


            if (n.getText().contains("nace")) {
                Matcher yearNacimientoM = yearNacimiento.matcher(n.getText());
                Matcher enteNacimientoM = EnteNacimiento.matcher(n.getText());
                if (yearNacimientoM.find()) {

                    try {
                        if (enteNacimientoM.find()) {


                            String year = "" + yearNacimientoM.group(0);
                            String[] yearSplit = n.getText().split("(\\d+)\\s(?!de)");


                            description = n.getText().split("(?<=nace )([\\s\\S].*?)(?<=, )");
                            if (!n.getText().contains(",(f")&&n.getText().contains("(f")){
                                yearDefuncion = n.getText().substring(n.getText().indexOf("("), n.getText().length());
                                try {
                                if (description.length != 0 && description[1].indexOf("(")>0) {

                                    descriptionClean = description[1].substring(0, description[1].indexOf("("));
                                    String [] nacimientoString= {year+"-"+mes+ "-"+dia ,
                                            year, yearDefuncion.replaceAll("[^A-Z0-9]", ""),
                                            enteNacimientoM.group(0) , descriptionClean };
                                    csVcreator.escribirArchivoNacimiento(nacimientoString);
                                    nacimientoList.add(new Nacimiento(Integer.parseInt(yearNacimientoM.group(0)),Integer.parseInt(yearDefuncion),enteNacimientoM.group(0),descriptionClean));
                                    xmLcreator.escribirArchivoNacimiento(nacimientoString);
                                }

                            } catch (ArrayIndexOutOfBoundsException e)   {
                            System.out.println("Error out of bounds en Acontecimieno del " +year+"-"+mes+ "-"+dia );

                            }

                            }
                            else{
                                yearDefuncion = "-1";
                                try {


                                    descriptionClean = description[1];
                                    String[] nacimientoString = {year + "-" + mes + " - " + dia,
                                            year, "-1",
                                            enteNacimientoM.group(0), descriptionClean};
                                    csVcreator.escribirArchivoNacimiento(nacimientoString);
                                    nacimientoList.add(new Nacimiento(Integer.parseInt(yearNacimientoM.group(0)), Integer.parseInt(yearDefuncion), enteNacimientoM.group(0), descriptionClean));
                                    xmLcreator.escribirArchivoNacimiento(nacimientoString);
                                }catch (ArrayIndexOutOfBoundsException e){
                                    System.out.println(descriptionClean);
                                }

                            }
                        }
                    } catch (NumberFormatException e) {

                    }
                }
            }
        }
    }
            /**

             * Funcion que devuelve las defunciones dentro de un dia

             * @param dia  el dia del año
             * @param mes  el mes del año

             */

    //Función que devuelve las defunciones dentro de un dia
    public void getDefunciones(int dia,int mes){
        int diaD=dia;
        int mesD= mes;
        String yearNacimiento="???";
        String descriptionClean="";
        Pattern yearDefuncion = Pattern.compile("(?<= de )([\\s\\S]\\d*?)(?= muere )");
        Pattern enteDefuncion = Pattern.compile("(?<=muere )([\\s\\S].*?)(?=,)");


        for (WebElement n : tagNameElementsP) {


            if (n.getText().contains("muere")) {
                Matcher yearDefuncionM = yearDefuncion.matcher(n.getText());
                Matcher enteDefuncionM = enteDefuncion.matcher(n.getText());
                if (yearDefuncionM.find()) {

                    try {

                        if (enteDefuncionM.find()) {
                            String year = "" + yearDefuncionM.group(0);
                            String[] yearSplit = n.getText().split("(\\d+)\\s(?!de)");


                            String[] description;
                            if (n.getText().contains(",")) {
                                description = n.getText().split("(?<=muere )([\\s\\S].*?)(?<=,)");
                            } else if (!n.getText().contains(","))
                                description = n.getText().split("(?<=muere )([\\s\\S].*?)([A-Z][a-z]*){2,}");
                            else description = n.getText().split("(?<=muere )([\\s\\S].*?)");
                            //    if (description[1].contains("(")) String descriptionClean=description[1].substring(0,description[1].indexOf("("));
                            if (n.getText().contains("(n.") && description.length != 0) {
                                yearNacimiento = n.getText().substring(n.getText().indexOf("("), n.getText().length());
                                if (description.length != 0 && description[1].indexOf("(")>0) descriptionClean = description[1].substring(0, description[1].indexOf("("));
                                             int yearNacimientoInt=        Integer.parseInt   (     yearNacimiento.replaceAll("[^A-Z0-9]", ""));
                                System.out.println("Defuncion: " + dia + " , " + mesDelAño(mes) + " , " +
                                        yearDefuncionM.group(0) + " , " + enteDefuncionM.group(0) +
                                        " , " + descriptionClean + " , " +
                                        yearNacimiento.replaceAll("[^A-Z0-9]", ""));
                                String [] defuncionString= {year+"-"+mes+ "-"+dia ,
                                        year, yearNacimiento.replaceAll("[^A-Z0-9]", ""),
                                        enteDefuncionM.group(0) , descriptionClean };

                                csVcreator.escribirArchivoDefunciones(defuncionString);
                                xmLcreator.escribirArchivoDefuncion(defuncionString);

                                defuncionList.add(new Defuncion(Integer.parseInt(yearDefuncionM.group(0)),
                                        yearNacimientoInt, enteDefuncionM.group(0),n.getText())) ;


                            }
                             else {
                                 String [] defuncionString= {year+"-"+mes+ "-"+dia ,
                                         year, "-1" ,enteDefuncionM.group(0) , n.getText() };
                                 csVcreator.escribirArchivoDefunciones(defuncionString);
                                xmLcreator.escribirArchivoDefuncion(defuncionString);

                                defuncionList.add(new Defuncion(Integer.parseInt(yearDefuncionM.group(0)),-1,enteDefuncionM.group(0),n.getText()));

                                    }

                        }
                        } catch(NumberFormatException e){



                    }
                }
            }
        }
    }
            /**

             * Funcion que devuelveel mes en letras

             * @param j  el mes del año

             */
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