package org.example;

public class Main {
    public static void main(String[] args) {
          ScrapperEfemerides se = new ScrapperEfemerides();
          se.getElementsP();

          se.driver.quit();
    }
}