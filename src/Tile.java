class Tile {
	public boolean north;
	public boolean south;
	public boolean east;
	public boolean west;

	public boolean pathExists(Tile target_tile, Direction direction) {
		switch (direction) {
			case NORTH:
				return (north && target_tile.south);
			case SOUTH:
				return (south && target_tile.north);
			case EAST:
				return (east && target_tile.west);
			case WEST:
				return (west && target_tile.east);
			default:
				return false;
		}
	}
}