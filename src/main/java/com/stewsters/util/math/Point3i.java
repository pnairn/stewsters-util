package com.stewsters.util.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Point3i {
    public int x;
    public int y;
    public int z;

    public Point3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
//        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3i)) return false;
        Point3i other = (Point3i) obj;
        if (x != other.x) return false;
        return y == other.y && z == other.z;
    }

    public List<Point3i> mooreNeighborhood() {
        ArrayList<Point3i> points = new ArrayList<Point3i>();

        for (int ox = -1; ox < 2; ox++) {
            for (int oy = -1; oy < 2; oy++) {
                if (ox == 0 && oy == 0) continue;

                points.add(new Point3i(x + ox, y + oy, z));
            }

        }


        Collections.shuffle(points);
        return points;
    }

    public List<Point3i> mooreNeighborhood3D() {
        ArrayList<Point3i> points = new ArrayList<Point3i>();

        for (int ox = -1; ox < 2; ox++) {
            for (int oy = -1; oy < 2; oy++) {
                for (int oz = -1; oz < 2; oz++) {
                    if (ox == 0 && oy == 0 && oz == 0) continue;

                    points.add(new Point3i(x + ox, y + oy, z + oz));
                }
            }
        }

        Collections.shuffle(points);
        return points;
    }

    @Override
    public String toString() {
        return String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z);
    }


    public Point3i copy() {
        return new Point3i(x, y, z);
    }
}
