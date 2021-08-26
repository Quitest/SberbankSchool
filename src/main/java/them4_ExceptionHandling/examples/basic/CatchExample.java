package ru.sbrf.javacourse.exeption.example.basic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CatchExample {

    public static void main(String[] args) {
        try {
            // По очереди удалять строчки.

            // Throws NumberFormatException.
            // Так как обработчика нет, то abnormal termination.
            Integer.parseInt("NotANumber");

            // Throws ClassNotFoundException.
            Class.forName("ClassThatCanNotBeFound");

            // Throws ArithmeticException.
            System.out.print(1 / 0);

            // Throws MalformedURLException (inherits from IOException).
            new URL("Some malformed URL");

            // Throws UnknownHostException (i.e. another IOException).
            new URL("http://sddswweweasdwedcdsfewfwefew.ru").getContent();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ArithmeticException | ClassNotFoundException e) {
            System.out.println("ArithmeticException | ClassNotFoundException");
        }
    }

}
