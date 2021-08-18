package theme1;

import java.util.Locale;
import java.util.Random;

/**
 * Основа взята с просторов <a href=https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string>
 * stackoverflow.com</a> и сильно обрезана. Основная цель использования - проверка работы метода Searcher.binarySearchGeneric()
 */
public class RandomString {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;
    private final Random random;
    private final char[] symbols;
    private final int maxLength;
    private char[] buf;

    public RandomString(int maxLength) {
        this.random = new Random();
        this.symbols = alphanum.toCharArray();
        this.buf = new char[10];
        this.maxLength = maxLength;
    }

    public String nextString() {
        int length = random.nextInt(maxLength);
        buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
