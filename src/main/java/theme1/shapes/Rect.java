package theme1.shapes;

public class Rect extends Shape {
    protected double width;
    protected double height;

    public Rect(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Стороны отрицательные");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}
