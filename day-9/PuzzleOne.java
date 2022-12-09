import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javafx.util.Pair;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-9/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 9");
            
            // No need to create an actual grid, just calculate numbers
            // Use a set to have locations already visited in the grid
            // can visit negative parts of the grid, like in x/y axis?

            Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();
            visited.add(new Pair<Integer, Integer>(0, 0)); // Both start at same spot

            Pair<Integer, Integer> currentH = new Pair<Integer, Integer>(0, 0);
            Pair<Integer, Integer> currentT = new Pair<Integer, Integer>(0, 0);
            Pair<Integer, Integer> oldH;

            // calculate distance between H and T

            int lastRelativePosition = 0; // 0 - same, 1 - cardinal, -1 diagonal
            String line;
            while((line = br.readLine()) != null) {

                char moveDirection = line.charAt(0);
                int moves = Integer.parseInt(line.split(" ")[1]);

                for(int i = 0; i < moves; i++) {
                    oldH = currentH;
                    currentH = move(currentH, moveDirection);
                    // check if T is adjacent in cardinal direction, or diagonally
                    // if adjacent don't move, update relative position of H to T
                    int distance = distance(currentH, currentT);
                    if(distance == 0) {
                        // same spot, don't move T, don't update visited
                        lastRelativePosition = 0;
                    } else if(distance(currentH, currentT) == 1) { // same or cardinal difference
                        // cardinal difference, don't move T, don't update visited
                        lastRelativePosition = 1;
                    } else if(distance == 2 && bothDifferent(currentH, currentT)) { // H is diagonal from T
                        // diagonal difference, don't move T, don't update visited
                        lastRelativePosition = -1;
                    } else {
                        // if H moved one step in cardinal direction away, move T that way
                        if(distance == 2) { // cardinal direction at this point
                            // move T same direction as H
                            currentT = move(currentT, moveDirection);
                        } else {
                            // if H moved from a diagonal away, move T to H old position
                            currentT = new Pair<Integer, Integer>(oldH.getKey(), oldH.getValue());
                        }
                        // whenever T moves try adding current position to visited
                        visited.add(currentT); // okay to add object, never deleting it, and pair compares K and V by value
                    }
                }
            }

            System.out.format("The length of visited positions is %d\n", visited.size());

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Calculates the difference between two coordinates.
     * 
     * @param num1 first coordinate
     * @param num2 second coordinate
     * @return the manhattan distance between them
     */
    public static int distance(Pair<Integer, Integer> num1, Pair<Integer, Integer> num2) {
        return Math.abs(num1.getKey() - num2.getKey()) + Math.abs(num1.getValue() - num2.getValue());
    }

    // X and Y coordinate are both different -> true, else -> false
    /**
     * Determines if the coordinates share any x or y values
     * 
     * @param num1 first coordinate
     * @param num2 second coordinate
     * @return true when they don't share X or Y values, otherwise false
     */
    public static boolean bothDifferent(Pair<Integer, Integer> num1, Pair<Integer, Integer> num2) {
        return Math.abs(num1.getKey() - num2.getKey()) > 0 && Math.abs(num1.getValue() - num2.getValue()) > 0;
    }

    // Move pair in cardinal direction
    public static Pair<Integer, Integer> move(Pair<Integer, Integer> ropeEnd, char direction) {
        if(direction == 'U') {
            return new Pair<Integer,Integer>(ropeEnd.getKey(), ropeEnd.getValue() + 1);
        } else if(direction == 'D') {
            return new Pair<Integer,Integer>(ropeEnd.getKey(), ropeEnd.getValue() - 1);
        } else if(direction == 'L') {
            return new Pair<Integer,Integer>(ropeEnd.getKey() - 1, ropeEnd.getValue());
        } else {
            // direction == 'R'
            return new Pair<Integer,Integer>(ropeEnd.getKey() + 1, ropeEnd.getValue());
        }
    }

    // Returns new ropeTail
    public static Pair<Integer, Integer> moveDiagonal(Pair<Integer, Integer> ropeTail, Pair<Integer, Integer> ropeHead) {
        int xDiff = ropeTail.getKey() - ropeHead.getKey();
        int yDiff = ropeTail.getValue() - ropeHead.getValue();
        
        if(xDiff < 0 && yDiff < 0) { // move right and down
            return new Pair<Integer,Integer>(ropeTail.getKey() + 1, ropeTail.getValue() + 1);
        } else if(xDiff > 0 && yDiff > 0) { // move left and up
            return new Pair<Integer,Integer>(ropeTail.getKey() - 1, ropeTail.getValue() - 1);
        } else if(xDiff < 0 && yDiff > 0) { // move right and up
            return new Pair<Integer,Integer>(ropeTail.getKey() + 1, ropeTail.getValue() - 1);
        } else { // move left and down
            return new Pair<Integer,Integer>(ropeTail.getKey() - 1, ropeTail.getValue() + 1);
        }
    }

}