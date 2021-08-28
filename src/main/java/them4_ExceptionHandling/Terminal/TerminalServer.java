package them4_ExceptionHandling.Terminal;

import them4_ExceptionHandling.Terminal.InnerExceptions.NoMoneyException;

public class TerminalServer implements Terminal {
    private double balance;
    private int pin;

    public TerminalServer(double balance) {
        this.balance = balance;
    }

    public TerminalServer() {
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double putCash(double amount) {
        balance += Math.abs(amount); //доп. защита. Не даем внести отрицательную сумму на счет, что приведет к незаконному обогащению. :)
        return balance;
    }

    @Override
    public double takeCash(double amount) throws NoMoneyException {
        if (balance < amount) {
            throw new NoMoneyException("Недостаточно денег на балансе.");
        }
        balance -= Math.abs(amount); //доп. защита. Не даем снять отрицательную сумму, что приведет к незаконному обогащению. :)
        return balance;
    }
}
