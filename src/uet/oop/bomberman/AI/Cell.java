package uet.oop.bomberman.AI;

public class Cell {
    //coordinates
    public int i, j;
    //parent cell for path
    public Cell parent;
    //Heuristic cost of the current cell
    public int heuristicCost;
    //final cost
    public int finalCost;
    //    G + H with
    //    G(n) the cost of the path from the star node to n
    //    H(n) the heuristic that estimates the cost of the cheapest path from n to the goal
    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
