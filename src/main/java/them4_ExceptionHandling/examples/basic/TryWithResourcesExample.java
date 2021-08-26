package ru.sbrf.javacourse.exeption.example.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TryWithResourcesExample {

    private static void someMethod(InputStream inputStream) {
        int[] a = new int[1];
        System.out.print(a[100]); // Throws ArrayIndexOutOfBoundsException.
    }

    public static void main(String[] args) throws Exception {
        try (FileInputStream inputStream = new FileInputStream("C:\\nonexistingfile.txt")) {
        // try (FileInputStream inputStream = new FileInputStream("C:\\Windows\\explorer.exe")) {
            someMethod(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file");
        } catch (Exception e) {
            System.out.println("Something strange happened");
            throw e;
        }
    }

}
