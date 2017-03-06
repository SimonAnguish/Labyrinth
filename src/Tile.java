class Tile {
	public boolean north;
	public boolean south;
	public boolean east;
	public boolean west;

	public Tile(Boolean north, Boolean south, Boolean east, Boolean west) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
	}

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

	public String toString() {
		String rtn = "\t";
		if (this.north) rtn += "|";
		rtn += "\n";
		if (this.west) rtn += "  --";
		else rtn += "\t";
		rtn += "+";
		if (this.east) rtn += "--";
		rtn += "\n\t";
		if (this.south) rtn += "|";
		rtn += "\n";

		return rtn;
	}
}