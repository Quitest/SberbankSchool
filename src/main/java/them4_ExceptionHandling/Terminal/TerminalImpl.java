package them4_ExceptionHandling.Terminal;

import them4_ExceptionHandling.Terminal.InnerExceptions.IllegalAmountException;
import them4_ExceptionHandling.Terminal.InnerExceptions.NoMoneyException;

public class TerminalImpl implements Terminal {
    private final TerminalServer terminalServer;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer terminalServer, PinValidator pinValidator) {
        this.terminalServer = terminalServer;
        this.pinValidator = pinValidator;
    }

    private void checkAmount(double amount) throws IllegalAmountException {
        if (amount % 100 != 0) {
            throw new IllegalAmountException("Не корректная сумма. Введите сумму кратную 100.");
        }
        if (amount<0){
            throw new IllegalAmountException("Сумма не может быть отрицательной.");
        }

    }

    @Override
    public double getBalance() {
        return terminalServer.getBalance();
    }

    @Override
    public double putCash(double money) throws IllegalAmountException {
        checkAmount(money);
        terminalServer.putCash(money);
        return terminalServer.getBalance();
    }

    @Override
    public double takeCash(double money) throws NoMoneyException, IllegalAmountException {
        checkAmount(money);
        terminalServer.takeCash(money);
        return terminalServer.getBalance();
    }
}
