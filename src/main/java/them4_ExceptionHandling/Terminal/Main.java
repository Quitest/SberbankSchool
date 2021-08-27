package them4_ExceptionHandling.Terminal;

public class Main {
    public static void main(String[] args) {
        Terminal t=  new TerminalImpl(new TerminalServer(), new PinValidator());
        System.out.println(t.putMoney(100));
    }
}
