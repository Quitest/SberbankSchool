package theme1.shapes;

/**
 * Квадрат - частный случай прямоугольника поэтому смотрим {@link theme1.shapes.Rect}
 */
public class Square extends Rect {
    public Square(double side) {
        //проверка на отрицательные значения аргумента стороны происходит в родительском конструкторе.
        super(side, side);
    }
}
