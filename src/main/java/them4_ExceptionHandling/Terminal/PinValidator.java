package them4_ExceptionHandling.Terminal;

public class PinValidator {
    private String pinCode;

    public static int charValidate(String symbol) throws NumberFormatException {
        int digit;
        try {
            digit = Integer.parseInt(symbol);
        }catch (NumberFormatException e){
            NumberFormatException nfe = new NumberFormatException("Не верный символ. Допускаются только цифры.");
            nfe.initCause(e);
            throw nfe;
        }
        return digit;
    }

    public static boolean isPinValidate(int pinCode){
        return false;
    }
}
