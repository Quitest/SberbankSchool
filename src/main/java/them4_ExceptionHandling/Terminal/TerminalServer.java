package them4_ExceptionHandling.Terminal;

public class TerminalServer implements Terminal{
    private double balance;
    private int pin;

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double putMoney(double amount) {
        checkAmount(amount);
        balance+=amount;
        return balance;
    }

    @Override
    public double transferMoney(double amount) {
        checkAmount(amount);
        balance-=amount;
        return balance;
    }

    //FIXME бросает эксепшен наследник RuntimeException, в данном случае это, наверное, не то, что нужно.
    // обдумать этот момнет. Да и вообще зачем тут кидать эксепшен? Дешевле обойтись обычным if.
    private void checkAmount(double amount){
        if (amount%100!=0){
            throw new IllegalArgumentException("Сумма не кратна 100");
        }
    }
}
