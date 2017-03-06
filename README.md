# Labyrinth
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
#### Functions
+ **initBoard** Sets the board up with the required 

---
## Enums
---
### DIRECTION
+ NORTH
+ SOUTH
+ EAST
+ WEST