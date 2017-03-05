# Labyrinth
**Table of Contents**

1. [Classes](#classes)
  + [Tile](#tile)
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

---
## Enums
---
### DIRECTION
+ NORTH
+ SOUTH
+ EAST
+ WEST