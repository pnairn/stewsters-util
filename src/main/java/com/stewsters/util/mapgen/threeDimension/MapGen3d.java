package com.stewsters.util.mapgen.threeDimension;

import com.stewsters.util.mapgen.CellType;
import com.stewsters.util.mapgen.threeDimension.brush.Brush3d;
import com.stewsters.util.mapgen.threeDimension.predicate.CellPredicate3d;
import com.stewsters.util.math.Point3i;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * http://www.roguebasin.com/index.php?title=Designing_Flexible,_Reusable_Algorithms
 */
public class MapGen3d {

    /**
     * Fills an area with a border
     *
     * @param map
     * @param fill
     * @param wall
     */
    public static void fillWithBorder(GeneratedMap3d map, CellType fill, CellType wall) {

        for (int x = 0; x < map.getWidthInTiles(); x++) {
            for (int y = 0; y < map.getHeightInTiles(); y++) {
                for (int z = 0; z < map.getDepthInTiles(); z++) {
                    if (x == 0 || y == 0 || z == 0
                            || x >= map.getWidthInTiles() - 1
                            || y >= map.getHeightInTiles() - 1
                            || z >= map.getDepthInTiles() - 1
                            ) {
                        map.setCellTypeAt(x, y, z, wall);
                    } else {
                        map.setCellTypeAt(x, y, z, fill);
                    }
                }
            }
        }

    }


    public static void fill(GeneratedMap3d map3d, CellPredicate3d predicate, Brush3d brush3d) {
        for (int ix = 0; ix < map3d.getWidthInTiles(); ix++) {
            for (int iy = 0; iy < map3d.getHeightInTiles(); iy++) {
                for (int iz = 0; iz < map3d.getDepthInTiles(); iz++) {

                    if (predicate.belongs(map3d, ix, iy, iz)) {
                        brush3d.draw(map3d, ix, iy, iz);
                    }

                }
            }
        }
    }


    /**
     * Flood fills on things that fit the predicate
     *
     * @param map
     * @param start
     * @param predicate
     * @param brush3d
     */
    public static void floodFill(GeneratedMap3d map, Point3i start, CellPredicate3d predicate, Brush3d brush3d) {

        LinkedList<Point3i> todo = new LinkedList<Point3i>();
        LinkedList<Point3i> match = new LinkedList<Point3i>();
        HashSet<Point3i> done = new HashSet<Point3i>();

        todo.push(start);

        Point3i p;
        while (todo.size() > 0) {
            p = todo.pop();

            if (!done.contains(p) && predicate.belongs(map, p.x, p.y, p.z)) {

                match.add(p);

                //todo: done list?
                if (p.x > 0)
                    todo.push(new Point3i(p.x - 1, p.y, p.z));
                if (p.x < map.getWidthInTiles() - 1)
                    todo.push(new Point3i(p.x + 1, p.y, p.z));
                if (p.y > 0)
                    todo.push(new Point3i(p.x, p.y - 1, p.z));
                if (p.y < map.getHeightInTiles() - 1)
                    todo.push(new Point3i(p.x, p.y + 1, p.z));
                if (p.z > 0)
                    todo.push(new Point3i(p.x, p.y, p.z - 1));
                if (p.z < map.getDepthInTiles() - 1)
                    todo.push(new Point3i(p.x, p.y, p.z + 1));

            }
            done.add(p);
        }

        // Goes over the whole map replacing a cell satisfying the predicate with the brush contents.
        for (Point3i point3i : match) {
            brush3d.draw(map, point3i.x, point3i.y, point3i.z);
        }

    }
}
