package com.stewsters.util.mapgen.twoDimension;

import com.stewsters.util.mapgen.CellType;
import com.stewsters.util.mapgen.twoDimension.brush.Brush2d;
import com.stewsters.util.mapgen.twoDimension.predicate.CellPredicate2d;
import com.stewsters.util.math.Point2i;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

/**
 * http://www.roguebasin.com/index.php?title=Designing_Flexible,_Reusable_Algorithms
 */
public class MapGen2d {

    /**
     * Fills an area with a border
     *
     * @param map  The map we are working on
     * @param fill The CellType we are filling the center with
     * @param wall The CellType we are filling the edge with
     */
    public static void fillWithBorder(GeneratedMap2d map, CellType fill, CellType wall) {

        for (int x = 0; x < map.getXSize(); x++) {
            for (int y = 0; y < map.getYSize(); y++) {

                if (x == 0 || y == 0 || x >= map.getXSize() - 1 || y >= map.getYSize() - 1) {
                    map.setCellTypeAt(x, y, wall);
                } else {
                    map.setCellTypeAt(x, y, fill);
                }
            }
        }

    }


    public static void fill(GeneratedMap2d map2d, CellPredicate2d predicate, Brush2d brush2d) {
        for (int ix = 0; ix < map2d.getXSize(); ix++) {
            for (int iy = 0; iy < map2d.getYSize(); iy++) {
                if (predicate.belongs(map2d, ix, iy)) {
                    brush2d.draw(map2d, ix, iy);
                }

            }
        }
    }

    // TODO: performance tests on floodfills

    /**
     * Flood fills on things that fit the predicate
     *
     * @param map       The map we are working on
     * @param start     The beginning of the flood fille
     * @param predicate The predicate to check
     * @param brush2d   The brush to fill
     */
    public static void floodFill(GeneratedMap2d map, Point2i start, CellPredicate2d predicate, Brush2d brush2d) {

        Deque<Point2i> todo = new ArrayDeque<Point2i>();
        ArrayList<Point2i> match = new ArrayList<>();
        HashSet<Point2i> done = new HashSet<Point2i>();

        todo.push(start);

        Point2i p;
        while (todo.size() > 0) {
            p = todo.pop();

            if (!done.contains(p) && predicate.belongs(map, p.x, p.y)) {

                match.add(p);

                //todo: done list?
                if (p.x > 0)
                    todo.push(new Point2i(p.x - 1, p.y));
                if (p.x < map.getXSize() - 1)
                    todo.push(new Point2i(p.x + 1, p.y));
                if (p.y > 0)
                    todo.push(new Point2i(p.x, p.y - 1));
                if (p.y < map.getYSize() - 1)
                    todo.push(new Point2i(p.x, p.y + 1));
            }
            done.add(p);
        }

        // Goes over the whole map replacing a cell satisfying the predicate with the brush contents.
        for (Point2i point2i : match) {
            brush2d.draw(map, point2i.x, point2i.y);
        }

    }
}
