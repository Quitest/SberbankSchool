package them4_ExceptionHandling.Terminal.innerExceptions;

import java.io.IOException;

public class NoMoneyException extends IOException {
    public NoMoneyException(String message) {
        super(message);
    }
}

