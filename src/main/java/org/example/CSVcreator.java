package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVcreator {

    public CSVcreator(){

    }

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
