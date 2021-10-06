package ru.sbt.patterns_hw;

//import ru.sbt.solid_hw.SalaryHtmlReportNotifier;

import ru.sbt.solid_hw.SalaryHtmlReportNotifier;
import sun.security.ec.point.Point;

import java.util.Objects;

public class Tractor {

    int[] position = new int[] { 0, 0 };
    int[] field = new int[] { 5, 5 };
    Orientation orientation = Orientation.NORTH;

    public void move(String command) {
        if (Objects.equals(command, "F")) {
            moveForwards();
        } else if (Objects.equals(command, "T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        position = orientation.move(position);
//        if (orientation == Orientation.NORTH) {
//            position = new int[] { position[0], position[1] + 1 };
//        } else if (orientation == Orientation.EAST) {
//            position = new int[] { position[0] + 1, position[1] };
//        } else if (orientation == Orientation.SOUTH) {
//            position = new int[] { position[0], position[1] - 1 };
//        } else if (orientation == Orientation.WEST) {
//            position = new int[] { position[0] - 1, position[1] };
//        }
        if (position[0] > field[0] || position[1] > field[1]) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        orientation = orientation.turn();
//        if (orientation == Orientation.NORTH) {
//            orientation = Orientation.EAST;
//        } else if (orientation == Orientation.EAST) {
//            orientation = Orientation.SOUTH;
//        } else if (orientation == Orientation.SOUTH) {
//            orientation = Orientation.WEST;
//        } else if (orientation == Orientation.WEST) {
//            orientation = Orientation.NORTH;
//        }
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
