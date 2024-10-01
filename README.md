# 8-Puzzle and A* Search Assignment

## Overview
This repository contains the implementation of the 8-puzzle problem using various search strategies, specifically breadth-first search (BFS) and A* search. The project is part of the COM1005 Machines and Intelligence course at the University of Sheffield.

## Assignment Details
- **Course**: COM1005 Machines and Intelligence
- **Due Date**: Wednesday 4th May 2022 at 15:00 (UK Time)
- **Weight**: 30% of the total course marks

## Problem Description
The 8-puzzle is a sliding puzzle consisting of a frame of numbered square tiles in random order with one tile missing. The objective is to place the tiles in order by making sliding moves that utilize the empty space.

### Rules of the Game
- You can slide any tile adjacent to the empty space into that space.
- Only 50% of the generated 8-puzzles are solvable.

## Provided Code
The provided code includes a search engine with the following search strategies:
- **BFS**: Breadth-First Search
- **DFS**: Depth-First Search (not implemented in this assignment)
- **Branch-and-Bound**: Same as BFS in this context due to uniform costs.
- **A\***: A* Search, with two heuristic methods for estimating remaining costs:
  - Hamming Distance
  - Manhattan Distance

## Tasks Completed
1. **Set up a GitHub Repository**: The project is maintained in a private GitHub repository to track versions and ensure individual work.
2. **Implement BFS**: Created classes `EpuzzleState`, `EpuzzleSearch`, and `RunEpuzzleBFS` to implement and test the 8-puzzle using BFS.
3. **Implement A\***: Adapted classes for A* search, including cost calculations and heuristic implementations.
4. **Performance Comparison**: Conducted experiments comparing the efficiency of BFS and A* search methods across various puzzle configurations.
5. **Report Generation**: Compiled a report detailing implementations, experimental results, and conclusions about the effectiveness of the different search strategies.
