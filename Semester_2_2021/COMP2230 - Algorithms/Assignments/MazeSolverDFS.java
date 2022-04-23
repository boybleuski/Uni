//================================================
// COMP2230 Assignment 1 
// Sam Dolbel - c3130069
//
//  This generates a maze using DFS (depth-first search) principles. 
//  Inputs are 'width' (int), 'height' (int) and 'file name' (String).
//  Outputs a text-based file with height, width, start and end cells, and cell openness.
//
// Date created: 19/09/2021
// Date modified: 08/10/2021
//================================================
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MazeSolverDFS {
    /* main method
        @param (String): Name of file containing maze details.
        @output: Maze solver details in command console.
    */
    public static void main(String[] args) {
        String input = "";
        // Make sure a file exists in that location.
        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            input = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the file in that location.");
            System.exit(0);
        }
        
        Random rng = new Random();

        // Split input into width, height, start, end and cell openness.
        int width = Integer.parseInt(input.substring(0, input.indexOf(",")));
        input = input.substring(input.indexOf(",")+1, input.length());

        int height = Integer.parseInt(input.substring(0, input.indexOf(":")));
        input = input.substring(input.indexOf(":")+1, input.length());

        int startNode = Integer.parseInt(input.substring(0, input.indexOf(":")));
        input = input.substring(input.indexOf(":")+1, input.length());

        int endNode = Integer.parseInt(input.substring(0, input.indexOf(":")));
        input = input.substring(input.indexOf(":")+1, input.length());

        int[] arrMazeCodes = getMazeOpenings(input, width, height);
        int currentNode = startNode;
        int previousNode = currentNode;

        // The 'correct' path - excludes retraced steps.  Used when backtracking.
        ArrayList<Integer> arrPath = new ArrayList<Integer>();
        arrPath.add(currentNode);

        // The total path, including retraced steps.  Used for output.
        ArrayList<Integer> arrTotalPath = new ArrayList<Integer>();
        arrTotalPath.add(currentNode);

        // When the program started searching the maze.
        long startTime = System.currentTimeMillis();

        // Keep searching until the end node is discovered.
        do {
            // Get all neighbouring nodes, then filter out the invalid neighbours.
            int[] arrAdjNodes = getNeighbourNodes(currentNode, width, height);
            ArrayList<Integer> arrValidNodes = new ArrayList<Integer>();
            
            // Can go left if the left neighbouring cell has 'right' openness.
            boolean left = (arrMazeCodes[arrAdjNodes[0]] == 1 || arrMazeCodes[arrAdjNodes[0]] == 3);
            // Can go right if this cell has 'right' openness.
            boolean right = (arrMazeCodes[currentNode] == 1  || arrMazeCodes[currentNode] == 3);
            // Can go up if the upper neighbouring cell has 'down' openness.
            boolean up = (arrMazeCodes[arrAdjNodes[2]] == 2 || arrMazeCodes[arrAdjNodes[2]] == 3);
            // Can go down if this cell has 'down' openness.
            boolean down = (arrMazeCodes[currentNode] == 2  || arrMazeCodes[currentNode] == 3);

            // Ensure the cell has not been entered yet, invalid otherwise.
            if (left && !arrTotalPath.contains(arrAdjNodes[0])) {
                arrValidNodes.add(arrAdjNodes[0]);
            }
            if (right && !arrTotalPath.contains(arrAdjNodes[1])) {
                arrValidNodes.add(arrAdjNodes[1]);
            }
            if (up && !arrTotalPath.contains(arrAdjNodes[2])) {
                arrValidNodes.add(arrAdjNodes[2]);
            }
            if (down && !arrTotalPath.contains(arrAdjNodes[3])) {
                arrValidNodes.add(arrAdjNodes[3]);
            }

            // If no valid cells, retrace steps.
            if (arrValidNodes.size() == 0) {
                arrPath.remove(arrPath.size()-1);
                currentNode = arrPath.get(arrPath.size()-1);
            } else {
                int nextNode = getRandomValidCell(arrValidNodes, rng);
                previousNode = currentNode;
                currentNode = nextNode;
                arrPath.add(currentNode);
            }
                
            // Regardless of outcome, add to total path.
            arrTotalPath.add(currentNode);
        }
        while (currentNode != endNode);

        // Build solver output.
        System.out.print("(");
        for (int i=0; i<arrTotalPath.size(); i++) {
            System.out.print(arrTotalPath.get(i));
            
            if (i != arrTotalPath.size()-1) {
                System.out.print(",");
            } else {
                System.out.println(")");
            }
        }

        System.out.println(arrTotalPath.size());
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }

    /* Convert a string of cell openings to an int array.
        @param input: String of cell openings.
        @param width: Width of the maze.
        @param height: Height of the maze.
        @return integer array of maze codes.
    */
    public static int[] getMazeOpenings(String input, int width, int height) {
        int[] arrMazeCodes = new int[(width*height)+1];

        for (int y=0; y<arrMazeCodes.length-1; y++) {
            arrMazeCodes[y+1] = Character.getNumericValue(input.charAt(y));
        }

        return arrMazeCodes;
    }

    /* Get an array of all cells around the current cell.
        @param currentNode: Node the maze solver is currently processing.
        @param width: Width of maze.
        @param height: Height of maze.
        @return array of nodes neighbouring 'currentNode'.
    */
    public static int[] getNeighbourNodes(int currentNode, int width, int height) {
        int arrNodes[] = {0, 0, 0, 0};
        int x = (currentNode-1) % width;
        int y = (currentNode-1) / width;
        
        // Checks that the solver does not try to get cells that are outside the maze.
        if (x > 0) {
            arrNodes[0] = currentNode - 1;
        }
        if (x < width-1) {
            arrNodes[1] = currentNode + 1;
        }
        if (y > 0) {
            arrNodes[2] = currentNode - width;
        }
        if (y < height-1) {
            arrNodes[3] = currentNode + width;
        }

        // Any cells still labelled '0' do not exist, meaning the current node is on the edge of the maze.
        return arrNodes;
    }

    /* Get a random valid cell from those in the list.
        @param arrValidCells: An ArrayList<Integer> of cells that are eligible for selection.
        @param rng: A random number generator.
        @return cell at index 'cell' in arrValidCells: Chosen valid cell.
    */
    public static int getRandomValidCell(ArrayList<Integer> arrValidCells, Random rng) {
        int cell = rng.nextInt(arrValidCells.size());

        return arrValidCells.get(cell);
    }
}