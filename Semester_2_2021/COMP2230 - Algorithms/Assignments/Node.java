//================================================
// COMP2230 Assignment 1 
// Sam Dolbel - c3130069
//
//
//
//
// Date created: 19/09/2021
// Date modified: 08/10/2021
//================================================
import java.io.*;

public class Node {
    // Node's x-coordinate.
    private int x;
    // Node's y-coordinate.
    private int y;
    // Index showing when the cell was first reached.
    private int vertexIndex;
    // The node before this one. While there can be multiple 'next' nodes, there will always be exactly 1 'previous' (except the start node).
    private Node prevNode;
    
    // Integer from 0-3 showing whether right and down cells are open.
    // 0: Both closed, 1: Right open, 2: Down open, 3: Both open
    private int cellOpenness;

    /* Constructor.
        @param xValue: x-coordinate.
        @param yValue: y-coordinate.
        @param vertexValue: cell index.
        @param previous: previous node.
    */
    public Node(int xValue, int yValue, int vertexValue, Node previous) {
        x = xValue;
        y = yValue;
        vertexIndex = vertexValue;
        prevNode = previous;
    }

    /* Getter for the x-coordinate.
        @return x: cell's x-coordinate
    */
    public int getX() {
        return x;
    }

    /* Getter for the y-coordinate.
        @return y: cell's y-coordinate
    */
    public int getY() {
        return y;
    }

    /* Getter for the vertex index.
        @return vertexIndex: index of the cell
    */
    public int getVertexIndex() {
        return vertexIndex;
    }

    /* Getter for the cell openness code.
        @return cellOpenness: integer from 0-3 showing where the cell opens to.
    */
    public int getCellOpenness() {
        return cellOpenness;
    }

    /* Getter for the previous node.
        @return prevNode: copy of the previous node.
    */
    public Node getPrevious() {
        return prevNode;
    }

    /* Add to the cell openness code.
        @param value: integer to add to code.  Will always be either 1 (for right) or 2 (for down).
    */
    public void addCellOpenness(int value) {
        cellOpenness = cellOpenness + value;
    }
}