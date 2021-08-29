package them4_ExceptionHandling.Terminal.innerExceptions;

import java.io.IOException;

public class IllegalAmountException extends IOException {
    public IllegalAmountException(String message) {
        super(message);
    }
}
