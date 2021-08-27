package them4_ExceptionHandling.Terminal;

//Интерфейс описывает работу с финансами и при этом оперирует значениями double. В реальных проектах надо помнить,
// что это плохая практика - использовать float, double для денежных операций - т.к. часто возникают ошибки округления
// при вычислениях. Яркий пример: System.out.println(1.13f + 10000f - 10000f);
// Из-за этого  могут не сходиться, так сказать, дЕбет с крЕдитом. :)
// Для хранения финансов в java принято использовтаь BigDecimal, однако, это просаживает производительность.
// Есть вариант хранения финансов в целочисленных примитивах типа int и long, но и тут свои нюансы, в которых
// не разбирался ввиду отсутствия необходимости на настоящее время.
public interface Terminal {
    /**
     * Проверка баланса счета.
     * @return сумму, находящуюся на счете.
     */
    double getBalance();

    /**
     * Положить денег на счет.
     * @param money сумма, которую необходимо положить на счет. Сумма должна быть кратна 100.
     * @return сумма, образовавшаяся на счету в итоге.
     */
    double putMoney(double money);

    /**
     * Снять указанную сумму денег со счета. <p>Прим.: название метода несовсем подходящее, т.к. подразумевается, что
     * сумма будет переведа куда-то, а по факту она будет просто изъята и выкинута. И всеже название оставлю таким
     * для напоминания самому себе, что имена надо давать не только осмысленные, но и раскрывающие суть :). </p>
     * @param money сумма перевода. Сумма должна быть кратна 100.
     * @return сумма, оставшаяся после перевода.
     */
    double transferMoney(double money);
}
