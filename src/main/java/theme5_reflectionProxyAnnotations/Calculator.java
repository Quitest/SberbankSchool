package theme5_reflectionProxyAnnotations;

public interface Calculator {
    /**
     * Расчет факториала числа/
     * @param number факториал чего считаем
     * @return факсториал
     */
    //По условию задачи возвращается int, хотя практичнее было бы long или вообще BigDecimal, какой-нибуль.
    int calc(int number);
}
