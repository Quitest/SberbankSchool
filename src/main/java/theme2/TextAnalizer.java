package theme2;

import lombok.Getter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс предназначен для анализа относительно небольших текстов согласно заданию.
 */
public class TextAnalizer implements Iterable {
    private final Charset charset;
    private final Path pathToFile;
    @Getter
    private List<String> text;

    public TextAnalizer(Path pathToFile, Charset charset) {
        this.charset = charset;
        this.pathToFile = pathToFile;
        try {
            text = Files.readAllLines(pathToFile, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет выполнить анализ текста согласно заданию:
     * <ul>
     * <li>подсчитать количество различных слов в файле - количество записей в Map;</li>
     * <li>подсчитать сколько раз каждое слово встречается в файле - значение value.</li>
     * </ul>
     *
     * @return {@code Map<String, Long>}, где:
     * <ul>
     * <li>key - слово, которое встречается в тексте;</li>
     * <li>value - сколько раз слово встречается в тексте.</li>
     * </ul>
     */
    public Map<String, Long> analize() {
        Map<String, Long> words = new HashMap<>();
        try (Stream<String> lines = Files.lines(pathToFile, charset)) {
            words = lines
                    .flatMap(line -> Arrays.stream(line.split("[^а-яА-ЯёЁ0-9]")))
                    .filter(e -> !e.isEmpty())
                    .map(word -> word.toLowerCase(Locale.ROOT))
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * Возвращает строки, номера которых задаются пользователем в произвольном порядке. Нумерация строк начинается с 0.
     * Если не указано ниодной строки, то возвращается {@code List<String>} с нулевым размером
     * Строки возвращаются в том порядке, в котором они и были указаны - не сортируются.
     *
     * @param numbers номера строк, которые необходимо получить
     * @return список запрошенных строк.
     */
    public List<String> getStrings(int... numbers) {
        List<String> requestedLines = new ArrayList<>();
        for (int n : numbers) {
            if (n < text.size()) {
                requestedLines.add(text.get(n));
            }
        }
        return requestedLines;
    }


    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < text.size() && text.get(currentIndex) != null;
            }

            @Override
            public String next() {
                return text.get(currentIndex++);
            }
        };
    }
}
