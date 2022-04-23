//================================================
// COMP2230 Assignment 1 
// Sam Dolbel - c3130069
//
//  This generates a maze using DFS (depth-first search) principles. 
//  Inputs are 'width' (int), 'height' (int) and 'file name' (String).
//  Outputs a text-based file with height, width, start and end cells, and cell openness.
//
// Date created: 19/09/2021
// Date modified: 09/10/2021
//================================================
import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class MazeGenerator {
    /* main method
        @param (int): width of maze.
        @param (int): height of maze.
        @param (String): name of output file.
        @output: file containing maze details.
    */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("\nError: 3 inputs are required - width, height and file name.\n");
            System.exit(0);
        }

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        String fileName = args[2];

        if (width < 2 && height < 2) {
            System.out.println("\nError: Maze must be larger than 1 x 1.\n");
            System.exit(0);
        }

        if (width < 1 || height < 1) {
            System.out.println("\nError: Width and height must be positive values.\n");
            System.exit(0);
        }

        Random rng = new Random();

        Node[][] maze = new Node[width][height];    // Store every cell as a 2D matrix of Nodes.

        int startX = rng.nextInt(width);
        int endX = 0;
        int x = startX;

        int startY = rng.nextInt(height);
        int endY = 0;
        int y = startY;
        int vertexIndex = 1;

        maze[startX][startY] = new Node(x, y, vertexIndex, null);   // Randomly choose a start node. A start node has no previous node, so prevNode is null.

        // Proceed until every cell has been reached.
        do {
            ArrayList<Integer> arrInvalidCells = getInvalidCells(x, y, width, height, maze);
            Node tempNode = maze[x][y]; // A node for temporary storage.

            // If any neighbouring cells are valid, choose a random valid cell.
            // Else, retreat through the maze until a valid cell appears.
            if (arrInvalidCells.size() < 4) {
                int direction = getRandomValidCell(arrInvalidCells, rng);

                switch (direction) {
                    case 0: // left. Later, we will add 1 to the left cell's openness.
                        x = x-1;
                        break;
                    case 1: // right. Add 1 to the current cell's openness.
                        maze[x][y].addCellOpenness(1);
                        x = x+1;
                        break;
                    case 2: // up. Later, we will add 2 to the top cell's openness.
                        y = y-1;
                        break;
                    case 3: // down. Add 2 to the current cell's openness.
                        maze[x][y].addCellOpenness(2);
                        y = y+1;
                        break;
                }

                // Increment vertexIndex whenever a new cell is reached for the first time.
                vertexIndex++;
                maze[x][y] = new Node(x, y, vertexIndex, tempNode);

                // If cell is open to the left, add 1 to the left cell's openness. If cell is open to the top, add 2 to the top cell's openness.
                if (direction == 0) {
                    maze[x][y].addCellOpenness(1);
                } else if (direction == 2) {
                    maze[x][y].addCellOpenness(2);
                }
            } else {
                x = tempNode.getPrevious().getX();
                y = tempNode.getPrevious().getY();
            }

            // Get the coordinates of the last cell.
            if (vertexIndex == width*height) {
                endX = x;
                endY = y;
            }
        }
        while (vertexIndex < width*height);

        // Generate the file output.
        String openness = "";
        for (y = 0; y < height; y++) {
            for (x = 0; x < width; x++) {
                openness += maze[x][y].getCellOpenness();
            }
        }

        String output = width + "," + height + ":" + ((startY*width) + (startX+1)) + ":" + ((endY*width) + (endX+1)) + ":" + openness;

        // Output to a text file and close the streams.
        try {
            File file = new File(fileName);
            FileOutputStream stream = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(stream);

            writer.write(output);
            writer.flush();
            stream.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* Build a list of neighbouring cells that cannot be reached from the current cell.
        @param x: The x-coordinate of the current cell.
        @param y: The y-coordinate of the current cell.
        @param width: The width of the maze.
        @param height: The height of the maze.
        @param maze: The current maze as a matrix of Nodes.
        @return arrInvalid: An ArrayList<Integer> of cells that are not eligible to be reached.
    */
    public static ArrayList<Integer> getInvalidCells(int x, int y, int width, int height, Node[][] maze) {
        ArrayList<Integer> arrInvalid = new ArrayList<Integer>();

        if (x == 0 || x > 0 && maze[x-1][y] != null) {
            arrInvalid.add(0);
        }
        if (x == width-1 || x < width-1 && maze[x+1][y] != null) {
            arrInvalid.add(1);
        }
        if (y == 0 || y > 0 && maze[x][y-1] != null) {
            arrInvalid.add(2);
        }
        if (y == height-1 || y < height-1 && maze[x][y+1] != null) {
            arrInvalid.add(3);
        }

        return arrInvalid;
    }

    /* Get a random valid cell, excluding those in the invalid cell list.
        @param arrInvalidCells: An ArrayList<Integer> of cells that are ineligible for selection.
        @param rng: A random number generator.
        @return direction: The direction of the next cell as an integer.
    */
    public static int getRandomValidCell(ArrayList<Integer> arrInvalidCells, Random rng) {
        int direction = rng.nextInt(4);
        while (arrInvalidCells.contains(direction)) {
            direction = rng.nextInt(4);
        }

        return direction;
    }
}