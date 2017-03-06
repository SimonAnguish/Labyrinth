/**
	@Author SimonAnguish
*/

class Tile {
	public boolean north;
	public boolean south;
	public boolean east;
	public boolean west;

	/**
		Tile
		Constructs a tile with the specified paths
		@param north Boolean if there is a path north on the tile
		@param south Boolean if there is a path south on the tile
		@param east Boolean if there is a path east on the tile
		@param west Boolean if there is a path west on the tile
	*/

	public Tile(Boolean north, Boolean south, Boolean east, Boolean west) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
	}

	/**
		pathExists
		Checks if there is a direct path between the two adjacent tiles
		@param targetTile The target tile we are checking if there is a direct path to
		@param direction Enum to determine which path we should check for
		@return boolean Whether there is a path directly between the two tiles
	*/
	public boolean pathExists(Tile targetTile, Direction direction) {
		switch (direction) {
			case NORTH:
				return (north && targetTile.south);
			case SOUTH:
				return (south && targetTile.north);
			case EAST:
				return (east && targetTile.west);
			case WEST:
				return (west && targetTile.east);
			default:
				return false;
		}
	}

	/**
		toString
		Prints an ascii image of the tile
		@return String The image of the tile in string format
	*/

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