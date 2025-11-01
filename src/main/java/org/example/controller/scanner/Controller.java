package org.example.controller.scanner;

import java.util.Scanner;

public class Controller {

    protected final Scanner scannerIn;

    public Controller(Scanner scannerIn) {
        this.scannerIn = scannerIn;
    }

    protected void println(String s){ System.out.println(s); }
    protected void print(String s){ System.out.print(s); }

    protected int lerInt() {
        while (true) {
            try { return Integer.parseInt(scannerIn.nextLine().trim()); }
            catch (Exception e) { print("Valor inválido, tente novamente: "); }
        }
    }

    protected Long lerLong() {
        while (true) {
            try { return Long.parseLong(scannerIn.nextLine().trim()); }
            catch (Exception e) { print("Valor inválido, tente novamente: "); }
        }
    }
}
