package theme5_reflectionProxyAnnotations.task1;

import theme5_reflectionProxyAnnotations.task6.Metric;

public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number факториал чего считаем
     * @return факсториал
     */
    //По условию задачи возвращается int, хотя практичнее было бы long или вообще BigDecimal, какой-нибудь.
    @Metric
    int calc(int number);

    int calcWithoutAnnotation(int number);
}
