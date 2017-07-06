# MazeSolver
Java program to solve ASCII mazes

Input is a file in the same directory as the .class files. Format is height of the maze on one line, followed by width on the next
and then the maze on the following lines.

Ex:
```
5
5
+---+
S | E
| | |
|   |
+---+
```

S signifies the start and E signifies the end. S is assumed to be along the first column. Currently the solver works,
although output is crude. The path found is not the shortest path.

TODO:

- [ ] Find the shortest path - create multiple paths, each keeping track of own state, compare # of steps to find shortest
- [ ] Print out solved path
