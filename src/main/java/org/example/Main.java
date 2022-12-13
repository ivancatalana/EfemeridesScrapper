package org.example;

import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException {
          ScrapperEfemerides se = new ScrapperEfemerides();
          se.getElementsP();
          se.driver.quit();
        System.out.println( se.nacimientoList.size());

    }
}