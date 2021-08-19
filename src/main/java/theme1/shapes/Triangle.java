package theme1.shapes;

public class Triangle extends Shape {
    private final double side1;
    private final double side2;
    private final double side3;

    public Triangle(double side1, double side2, double side3) {
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Стороны отрицательные");
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /**
     * Вычисление площади треугольника по <a href=https://ru.wikipedia.org/wiki/%D0%A4%D0%BE%D1%80%D0%BC%D1%83%D0%BB%D0%B0_%D0%93%D0%B5%D1%80%D0%BE%D0%BD%D0%B0>
     * формуле Герона</a>
     *
     * @return площадь треугольника
     */
    @Override
    public double getArea() {
        double p = 0.5 * (side1 + side2 + side3); //полупериметр
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
}
