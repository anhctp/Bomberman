package uet.oop.bomberman.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    //costs for vertical / horizontal moves
    public static final int V_H_COST = 1;
    // Cells of our grid
    private Cell[][] grid;
    // Open Cells: the set of nodes to be evaluated
    // We put cells with lowest cost in first
    private PriorityQueue<Cell> openCells;
    //Closed Cells: the set of nodes already evaluated
    private boolean[][] closedCells;
    private int startI, startJ;
    private int endI, endJ;

    public AStar(int width, int height, int si, int sj, int ei, int ej, List<List<Integer>> blocks) {
        grid = new Cell[width][height];
        closedCells = new boolean[width][height];
        openCells = new PriorityQueue<Cell>((Cell c1, Cell c2) -> {
            return c1.finalCost < c2.finalCost ? -1 : (c1.finalCost > c2.finalCost ? 1 : 0);
        });

        startCell(si, sj);
        endCell(ei, ej);

//        init heuristic and cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
            }
        }
        grid[startI][startJ].finalCost = 0;

        //Put the blocks on the grid
        for (int i = 0; i < blocks.get(0).size(); i++) {
            addBlockOnCell(blocks.get(0).get(i), blocks.get(1).get(i));
        }
    }

    public void addBlockOnCell(int i, int j) {
        grid[i][j] = null;
    }

    public void startCell(int i, int j) {
        startI = i;
        startJ = j;
    }

    public void endCell(int i, int j) {
        endI = i;
        endJ = j;
    }

    public void updateCostIfNeeded(Cell current, Cell t, int cost) {
        if (t == null || closedCells[t.i][t.j]) {
            return;
        }
        int tFinalCost = t.heuristicCost + cost;
        boolean isOpen = openCells.contains(t);

        if (!isOpen || tFinalCost < t.finalCost) {
            t.finalCost = tFinalCost;
            t.parent = current;

            if (!isOpen) {
                openCells.add(t);
            }
        }
    }

    public void process() {
        //We add the star location to open list
        try {
            openCells.add(grid[startI][startJ]);
        } catch (NullPointerException e) {
            System.out.println("null");
        }

        Cell current = null;

        while (true) {
            current = openCells.poll();

            if (current == null) {
                break;
            }

            closedCells[current.i][current.j] = true;

            if (current.equals(grid[endI][endJ])) {
                break;
            }

            Cell t = null;

            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }
            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }
            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }
            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }
        }
    }

    public List<List<Integer>> getPath() {
        List<List<Integer>> path = new ArrayList<List<Integer>>();
        path.add(new ArrayList<Integer>());
        path.add(new ArrayList<Integer>());
        if (closedCells[endI][endJ]) {
            Cell current = grid[endI][endJ];
            while (current.parent != null) {
                path.get(0).add(current.i);
                path.get(1).add(current.j);
                current = current.parent;
            }
        } else path = null;
        return path;
    }
}