import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 15");

            // Scanner -> Beacon
            Map<Pair<Integer, Integer>, Pair<Integer, Integer>> devices = new HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>>();
            // x -> y -> 'S' | 'B' | '#'
            // 'S' - Scanner, 'B' - Beacon, '#' - Can't have "The Beacon"
            Map<Integer, Map<Integer, String>> caveMap = new HashMap<Integer, Map<Integer, String>>(Integer.MAX_VALUE);

            String line;
            String[] tokens;
            int highestValueX = 0, yPair = 0; // if its a scanner have to go that + distance to beacon
            boolean isScanner = false;
            while((line = br.readLine()) != null) {
                // Read in one scanner and beacon per line
                tokens = line.split(" ");
                int xScanner = Integer.parseInt(tokens[2].substring(2, tokens[2].indexOf(",")));
                int yScanner = Integer.parseInt(tokens[3].substring(2, tokens[3].indexOf(":")));

                int xBeacon = Integer.parseInt(tokens[8].substring(2, tokens[8].indexOf(",")));
                int yBeacon = Integer.parseInt(tokens[9].substring(2, tokens[9].length()));

                if(xScanner > highestValueX) {
                    highestValueX = xScanner;
                    yPair = yScanner;
                    isScanner = true;
                }
                if(xBeacon > highestValueX) {
                    highestValueX = xBeacon;
                    yPair = yBeacon;
                    isScanner = false;
                }
                
                // Put them into devices mapping
                devices.put(new Pair<Integer,Integer>(xScanner, yScanner), new Pair<Integer,Integer>(xBeacon, yBeacon));

                // Put them in caveMap location no matter what
                Map<Integer, String> column = caveMap.get(xScanner);
                if(column == null) {
                    column = new HashMap<Integer, String>();
                    caveMap.put(xScanner, column);
                }
                column.put(yScanner, "S");

                Map<Integer, String> column2 = caveMap.get(xBeacon);
                if(column2 == null) {
                    column2 = new HashMap<Integer, String>();
                    caveMap.put(xBeacon, column2);
                }
                column2.put(yBeacon, "B");
            }

            // Doesn't work. The distance between a beacon and scanner, could make
            // the highest value needed to check much higher than the furthest scanner + beacon distance
            // System.out.format("Highest value %d is scanner %b\n", highestValueX, isScanner);
            // // Find distance from highest Scanner to beacon and add to highest x value
            // if(isScanner) {
            //     Pair<Integer, Integer> scanner = new Pair<Integer, Integer>(highestValueX, yPair);
            //     Pair<Integer, Integer> beacon = devices.get(scanner);
            //     int manhattanDistance = Math.abs(scanner.getKey() - beacon.getKey()) + Math.abs(scanner.getValue() - beacon.getValue());
            //     highestValueX += manhattanDistance;
            //     System.out.format("New Highest value %d\n", highestValueX);
            // }

            // Draw diamond of spots around each sensor based on closed beacon
            // Putting locations into caveMap
            
            // Calculate manhattan distance from each sensor -> beacon
            // Math.abs(xSensor - xBeacon) + Math.abs(ySensor - yBeacon)

            // Cheap way would be drawing a square from manhattan distance diagonal
            // Then checking manhattan distance to each square. If less than or equal
            // to manhattan distance and value is null add "#"

            // Could only update y=2_000_000 row
            for(Pair<Integer, Integer> scanner : devices.keySet()) {
                Pair<Integer, Integer> beacon = devices.get(scanner);
                int manhattanDistance = Math.abs(scanner.getKey() - beacon.getKey()) + Math.abs(scanner.getValue() - beacon.getValue());
                for(int y = (scanner.getValue() - manhattanDistance); y <= (scanner.getValue() + manhattanDistance); y++) {
                    if(y == 2_000_000) {
                        for(int x = (scanner.getKey() - manhattanDistance); x <= (scanner.getKey() + manhattanDistance); x++) {
                            int manhattanDistanceFromScanner = Math.abs(scanner.getKey() - x) + Math.abs(scanner.getValue() - y);
                            if(manhattanDistanceFromScanner <= manhattanDistance) {
                                Map<Integer, String> column = caveMap.get(x);
                                if(column == null) {
                                    column = new HashMap<Integer, String>();
                                    caveMap.put(x, column);
                                }
                                if(column.get(y) == null)
                                    column.put(y, "#");
                            }
                        }
                    }
                }
            }
            System.out.println("Finished counting per row");

            // For the line y=2_000_000
            // determine the number of locations that can't have the beacon
            Map<Integer, String> column;
            int numLocationsNoBeacon = 0;
            for(int x = -1_000_000; x <= 10_000_000; x++) {
                if(caveMap.get(x) != null && caveMap.get(x).get(2_000_000) == "#") // for y=2_000_000
                    numLocationsNoBeacon++;
            }

            System.out.format("Number of locations that can't have the beacon %d\n", numLocationsNoBeacon);

            // printCaveMap(caveMap);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printCaveMap(Map<Integer, Map<Integer, String>> caveMap) {
        System.out.println("               1    1    2    2");
        System.out.println("     0    5    0    5    0    5");
        for(int x = -2; x <= 22; x++) {
            System.out.format("%2d ", x);
            for(int y = -2; y <= 25; y++) {
                Map<Integer, String> column = caveMap.get(x);
                if(column == null)
                    System.out.print(".");
                else
                    System.out.print(caveMap.get(x).get(y) == null ? "." : caveMap.get(x).get(y));
            }
            System.out.println();
        }
    }

}