package them4_ExceptionHandling.Terminal;

import them4_ExceptionHandling.Terminal.InnerExceptions.IllegalAmountException;
import them4_ExceptionHandling.Terminal.InnerExceptions.NoMoneyException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String RUB = "\u20BD";

    public static void main(String[] args) {
        TerminalImpl t = new TerminalImpl(new TerminalServer(200), new PinValidator());
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            showMainMenu();
            choice = scanner.nextLine();
            switch (choice) { //ради такого синтаксиса switch'а пришлось использовать JDK 14.
                case "1" -> System.out.printf("Текущий баланс: %.2f %s %n", t.getBalance(), RUB);
                case "2" -> {
                    do {
                        System.out.println("Введите сумму (0 - выход): ");
                        String amount = scanner.nextLine();
                        if ("0".equals(amount)) {
                            break;
                        }
                        try {
                            System.out.printf("Операция успешно выполнена. Текущий баланс: %.2f %s %n",
                                    t.putCash(Double.parseDouble(amount)), RUB);
                        } catch (IllegalAmountException e) {
                            System.err.println(e.getMessage());
                        } catch (NumberFormatException e) {
                            System.err.println("Введено неверное значение. Допускаются только численные значения.");
                        }
                    }
                    while (true);
                }
                case "3" -> {
                    do {
                        System.out.println("Введите сумму (0 - выход): ");
                        String amount = scanner.nextLine();
                        if ("0".equals(amount)) {
                            break;
                        }
                        try {
                            System.out.printf("Операция успешно выполнена. Текущий баланс: %.2f %s %n ",
                                    t.takeCash(Double.parseDouble(amount)), RUB);
                        } catch (NoMoneyException | IllegalAmountException e) {
                            System.err.println(e.getMessage());
                        }catch (NumberFormatException e){
                            System.err.println("Введено неверное значение. Допускаются только численные значения.");
                        }
                    }while (true);
                }
                case "0" -> System.out.println("До свидания");
                default -> //можно так же использовать exception, но, думаю, в этой ситуации дешевле и правильней поступить так.
                        System.out.println("Такого пункта меню нет. Введине другое значение.");
            }

        } while (!"0".equals(choice));
    }

    public static void showMainMenu() {
        System.out.println("\nВыберите пункт меню:\n" +
                "1. Проверить баланс.\n" +
                "2. Внести наличные.\n" +
                "3. Снять наличные.\n" +
                "0. Выход.");
    }
}
