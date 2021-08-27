package them4_ExceptionHandling.Terminal;

public class TerminalImpl implements Terminal {
//    private double balance;
    private final TerminalServer terminalServer;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer terminalServer, PinValidator pinValidator) {
        this.terminalServer = terminalServer;
        this.pinValidator = pinValidator;
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public double putMoney(double money) {
        terminalServer.putMoney(money);
        return terminalServer.getBalance();
    }

    @Override
    public double transferMoney(double money) {
        terminalServer.transferMoney(money);
        return terminalServer.getBalance();
    }
}
