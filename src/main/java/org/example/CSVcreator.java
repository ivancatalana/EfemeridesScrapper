package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**

 * Esta clase define metodos que conectarán con la web efemerides.com para extraer la información

 * @author: Ivan Morales Mirete

 * @version: 13/12/2022/A

 */

public class CSVcreator {

    /**

     * Método que escribe el número de ítems acontecimiento en el fichero Acontecimientos.csv

     * @param acontecimiento  los elementos a escribir

     */
    public void escribirArchivoAcontecimiento(String [] acontecimiento){

        File file= new File("src/Acontecimientos.csv");
         if (!file.exists())
        try {
            file.createNewFile();
            CSVWriter csvWriter= new CSVWriter(new FileWriter(file,true));
            csvWriter.writeNext(new String[]{"date", "year","description"});
            csvWriter.writeNext(acontecimiento);
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            CSVWriter csvWriter= new CSVWriter(new FileWriter(file,true));
            csvWriter.writeNext(acontecimiento);
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**

     * Método que escribe el número de ítems nacimiento en el fichero Acontecimientos.csv

     * @param nacimiento  los elementos a escribir

     */
    public void escribirArchivoNacimiento(String [] nacimiento) {

        File file = new File("src/Nacimientos.csv");
        if (!file.exists())
            try {
                file.createNewFile();
                CSVWriter csvWriter= new CSVWriter(new FileWriter(file,true));
                csvWriter.writeNext(new String[]{"date", "yearBorn","yearDead","Name","description"});
                csvWriter.writeNext(nacimiento);
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        else {
            try {
                CSVWriter csvWriter= new CSVWriter(new FileWriter(file,true));
                csvWriter.writeNext(nacimiento);
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**

     * Método que escribe el número de valores de los ítems defunciones en el fichero Acontecimientos.csv

     * @param defuncion  los elementos a escribir

     */
    public void escribirArchivoDefunciones(String [] defuncion) {

        File file = new File("src/Defunciones.csv");
        if (!file.exists())
            try {
                file.createNewFile();
                CSVWriter csvWriter= new CSVWriter(new FileWriter(file,true));
                csvWriter.writeNext(new String[]{"date", "yearDead","yearBorn","Name","description"});
                csvWriter.writeNext(defuncion);
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        else {
            try {
                CSVWriter csvWriter= new CSVWriter(new FileWriter(file,true));
                csvWriter.writeNext(defuncion);
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
