package com.stewsters.util.pathing.threeDimention.shared;

/**
 * The description for the data we're pathfinding over. This provides the contract
 * between the data being searched (i.e. the in game map) and the path finding
 * generic tools
 *
 * @author Kevin Glass
 */
public interface TileBasedMap3d {
    /**
     * Get the width of the tile map. The slightly odd name is used
     * to distiguish this method from commonly used names in game maps.
     *
     * @return The number of tiles across the map
     */
    public int getWidthInTiles();

    /**
     * Get the height of the tile map. The slightly odd name is used
     * to distiguish this method from commonly used names in game maps.
     *
     * @return The number of tiles down the map
     */
    public int getHeightInTiles();

    /**
     * Get the depth of the tile map. The slightly odd name is used
     * to distiguish this method from commonly used names in game maps.
     *
     * @return The number of tiles down the map
     */
    public int getDepthInTiles();


    /**
     * Notification that the path finder visited a given tile. This is
     * used for debugging new heuristics.
     *
     * @param x The x coordinate of the tile that was visited
     * @param y The y coordinate of the tile that was visited
     */
    public void pathFinderVisited(int x, int y, int z);

    /**
     * Check if the given location is blocked, i.e. blocks movement of
     * the supplied mover.
     *
     * @param mover    The mover that is potentially moving through the specified
     *                 tile.
     * @param pathNode The location of the tile to check
     * @return True if the location is blocked
     */
    public boolean isBlocked(Mover3d mover, PathNode3d pathNode);

    public boolean isBlocked(Mover3d mover, int x, int y, int z);

    /**
     * Get the cost of moving through the given tile. This can be used to
     * make certain areas more desirable. A simple and valid implementation
     * of this method would be to return 1 in all cases.
     *
     * @param mover The mover that is trying to move across the tile
     * @param sx    The x coordinate of the tile we're moving from
     * @param sy    The y coordinate of the tile we're moving from
     * @param tx    The x coordinate of the tile we're moving to
     * @param ty    The y coordinate of the tile we're moving to
     * @return The relative cost of moving across the given tile
     */
    public float getCost(Mover3d mover, int sx, int sy, int sz, int tx, int ty, int tz);


}