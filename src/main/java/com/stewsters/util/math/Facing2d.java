package com.stewsters.util.math;


public enum Facing2d {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1);

    public int x;
    public int y;

    Facing2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Facing2d randomCardinal() {
        switch (MatUtils.getIntInRange(0, 3)) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            default:
                return WEST;
        }
    }

    public static Facing2d randomDiagonal() {
        switch (MatUtils.getIntInRange(0, 8)) {
            case 0:
                return NORTH;
            case 1:
                return NORTHEAST;
            case 3:
                return EAST;
            case 4:
                return SOUTHEAST;
            case 5:
                return SOUTH;
            case 6:
                return SOUTHWEST;
            case 7:
                return WEST;
            default:
                return NORTHWEST;

        }
    }

}
