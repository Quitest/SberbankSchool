package them4_ExceptionHandling.Terminal.controller;

import them4_ExceptionHandling.Terminal.innerExceptions.IllegalAmountException;
import them4_ExceptionHandling.Terminal.innerExceptions.NoMoneyException;
import them4_ExceptionHandling.Terminal.PinValidator;
import them4_ExceptionHandling.Terminal.Terminal;
import them4_ExceptionHandling.Terminal.model.TerminalServer;

public class TerminalImpl implements Terminal {
    private final TerminalServer terminalServer;
    private final PinValidator pinValidator; //WTF почему проверка пина происходить должна на терминале, а не на серверной стороне?

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


    public boolean loginToTerminalServer(int pin){
        return terminalServer.authOnServer(pin);
    }
}
