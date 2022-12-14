import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 13");

            // Reading in rock structure
            // x,y coordinates
            // x -> how far right, y -> how far down, like drawing in a window

            // Create map of rock area + adding sand
            // Move sand from spawn region
            // When a piece of sand moves further south (its y) than the southern most rock structure
            // it will fall infinitely

            // Think this is a problem that's good for a sparse graph
            // Could use a standard array, but I think a better method would using
            // a map for the x coordinate

            // map x -> (map y -> rock or sand) 
            Map<Integer, Map<Integer, String>> rockStructure = new HashMap<Integer, Map<Integer, String>>();
            // Rock -> #
            // Sand -> O

            // Test showed all lines are straight

            String line;
            String[] tokens;
            int highestY = 0;
            while((line = br.readLine()) != null) {
                tokens = line.split(" ");
                int xCoordOld = Integer.parseInt(tokens[0].substring(0, tokens[0].indexOf(",")));
                int yCoordOld = Integer.parseInt(tokens[0].substring(tokens[0].indexOf(",")+1));
                if(yCoordOld > highestY) highestY = yCoordOld;
                for(int i = 2; i < tokens.length; i+=2) {
                    int xCoord = Integer.parseInt(tokens[i].substring(0, tokens[i].indexOf(",")));
                    int yCoord = Integer.parseInt(tokens[i].substring(tokens[i].indexOf(",")+1));
                    if(yCoord > highestY) highestY = yCoord;
                    
                    // Either line up, down, left, or right
                    if(xCoordOld - xCoord > 0) {
                        // Line moving right to left
                        for(int x = xCoord; x <= xCoordOld; x++) {
                            Map<Integer, String> column = rockStructure.get(x);
                            if(column == null) {
                                column = new HashMap<Integer, String>();
                                rockStructure.put(x, column);
                            }
                            column.put(yCoord, "#"); // y is the same
                        }
                    } else if(xCoordOld - xCoord < 0) {
                        // Line moving left to right
                        for(int x = xCoordOld; x <= xCoord; x++) {
                            Map<Integer, String> column = rockStructure.get(x);
                            if(column == null) {
                                column = new HashMap<Integer, String>();
                                rockStructure.put(x, column);
                            }
                            column.put(yCoord, "#"); // y is the same
                        }
                    } else if(yCoordOld - yCoord > 0) {
                        // Line moving bottom to top'
                        // Only need to get the column once
                        Map<Integer, String> column = rockStructure.get(xCoord);
                        for(int y = yCoord; y <= yCoordOld; y++) {
                            if(column == null) {
                                column = new HashMap<Integer, String>();
                                rockStructure.put(xCoord, column);
                            }
                            column.put(y, "#"); // y is the same
                        }
                    } else if(yCoordOld - yCoord < 0) {
                        // Line moving top to bottom
                        Map<Integer, String> column = rockStructure.get(xCoord);
                        for(int y = yCoordOld; y <= yCoord; y++) {
                            if(column == null) {
                                column = new HashMap<Integer, String>();
                                rockStructure.put(xCoord, column);
                            }
                            column.put(y, "#"); // y is the same
                        }
                    }
                    xCoordOld = xCoord;
                    yCoordOld = yCoord;
                }
            }

            // Perform sand movements and falling
            // When sands position is past the lowest rock (highest y value)

            int sandPlaced = 0;
            ArrayList<Pair<Integer, Integer>> sandPlacedCoords = new ArrayList<>();
            Pair<Integer, Integer> sandLocation = new Pair<Integer, Integer>(500, 0);
            while(rockStructure.get(500).get(0) == null) {
                sandLocation = new Pair<Integer, Integer>(500, 0);

                boolean sandCanMove = true;
                while(sandCanMove) {
                    int x = sandLocation.getKey();
                    int y = sandLocation.getValue();

                    if(y+1 == highestY+2) { // infinite floor
                        Map<Integer, String> column = rockStructure.get(x);
                        if(column == null) {
                            column = new HashMap<Integer, String>();
                        }
                        column.put(y, "O");
                        sandCanMove = false;
                        break;
                    }

                    if(rockStructure.get(x) == null || (rockStructure.get(x) != null && rockStructure.get(x).get(y+1) == null)) { // Try to move down one
                        if(rockStructure.get(x) == null)
                            rockStructure.put(x, new HashMap<Integer, String>());
                        sandLocation = new Pair<Integer,Integer>(x, y+1);
                    } else if(rockStructure.get(x-1) == null || (rockStructure.get(x-1) != null && rockStructure.get(x-1).get(y+1) == null)) { // Try to move down left one
                        if(rockStructure.get(x-1) == null)
                            rockStructure.put(x-1, new HashMap<Integer, String>());
                        sandLocation = new Pair<Integer,Integer>(x-1, y+1);
                    } else if(rockStructure.get(x+1) == null || (rockStructure.get(x+1) != null && rockStructure.get(x+1).get(y+1) == null)) {
                        if(rockStructure.get(x+1) == null)
                            rockStructure.put(x+1, new HashMap<Integer, String>());
                        sandLocation = new Pair<Integer,Integer>(x+1, y+1);
                    } else {
                        Map<Integer, String> column = rockStructure.get(x);
                        if(column == null) {
                            column = new HashMap<Integer, String>();
                        }
                        column.put(y, "O");
                        sandCanMove = false;
                    }
                }

                // When sand can't move increase sandPlaced
                sandPlacedCoords.add(sandLocation);
                sandPlaced++;
            }

            System.out.format("Highest y value %d\n", highestY);
            System.out.format("Sand placed %d\n", sandPlaced);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}