package ru.sbt.patterns_hw;

//public enum Orientation {
//    NORTH, WEST, SOUTH, EAST
//}
public enum Orientation {
    NORTH {
        Orientation turn() {
            return EAST;
        }

        int[] move(int[] position) {
            return new int[]{position[0], position[1] + 1};
        }
    },
    EAST {
        Orientation turn() {
            return SOUTH;
        }

        int[] move(int[] position) {
            return new int[]{position[0] + 1, position[1]};
        }
    },
    SOUTH {
        Orientation turn() {
            return WEST;
        }

        int[] move(int[] position) {
            return new int[]{position[0], position[1] - 1};
        }
    },
    WEST {
        Orientation turn() {
            return NORTH;
        }

        int[] move(int[] position) {
            return new int[]{position[0] - 1, position[1]};
        }
    };

    abstract int[] move(int[] position);

    abstract Orientation turn();
}