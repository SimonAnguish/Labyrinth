# Labyrinth
![Labyrinth](http://www.boardgamecapital.com/game_images/labyrinth.jpg)

![GUI](https://github.com/SimonAnguish/Labyrinth/blob/master/docs/gui_3-23-17.PNG?raw=true)
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
+ **rotate** Rotates the tile a specified number of times clockwise.
  + **int** The number of times to rotate the Tile. Will often be 1.

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