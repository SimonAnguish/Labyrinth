# Labyrinth
![Labyrinth](http://www.boardgamecapital.com/game_images/labyrinth.jpg)
---
**Table of Contents**

1. [Classes](#classes)
  + [Tile](#tile)
  + [Board](#board)
2. [Enums](#enums)
  + [DIRECTION](#direction)

---
## Classes
---
### Tile
#### Variables
+ **Boolean** north
+ **Boolean** south
+ **Boolean** east
+ **Boolean** west

#### Functions
+ **pathBetween** Checks if there is a path between the current and the target tile in the specified direction.
  + **Tile** target_tile
  + **DIRECTION** direction

### Board
#### Variables
+ **Tile[][]** board
+ **Tile** tileInHand

#### Functions
+ **initBoard** Sets the board up with the required pieces and a random orientation of the other pieces
+ **generateTiles** Generates the appropriate number of the specific tiles, and shuffles them into a List
+ **getTileAt** Returns the Tile at the specified location on the board
  + **int** row
  + **int** column

---
## Enums
---
### DIRECTION
+ NORTH
+ SOUTH
+ EAST
+ WEST