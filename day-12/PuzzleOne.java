import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 12");

            ArrayList<ArrayList<Character>> heightMap = new ArrayList<ArrayList<Character>>();

            String line;
            int xStart = 0, yStart = 0;
            int x = 0;
            while((line = br.readLine()) != null) {
                ArrayList<Character> row = new ArrayList<>();
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == 'S') {
                        xStart = x;
                        yStart = i;
                    }
                    row.add(line.charAt(i));
                }
                heightMap.add(row);
                x++;
            }

            Queue<Pair<Integer, Integer>> nextMoves = new LinkedList<Pair<Integer, Integer>>();

            // Index visited
            Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();
            visited.add(new Pair<Integer,Integer>(xStart, yStart));
            nextMoves.add(new Pair<Integer,Integer>(xStart, yStart));

            int numberOfSteps = 0;
            while(nextMoves.size() > 0) {
                
                int currentStepTiles = nextMoves.size();
                numberOfSteps++;
                for(int i = 0; i < currentStepTiles; i++) { // level by level search
                    Pair<Integer, Integer> tile = nextMoves.remove();
                    char tileChar = heightMap.get(tile.getKey()).get(tile.getValue());
                    int curX = tile.getKey();
                    int curY = tile.getValue();
                    if(tileChar == 'E') {
                        System.out.format("Steps taken: %d", (numberOfSteps-1));
                        break;
                    }

                    // add next moves if not visited/can move
                    // Try to add four possible squares to queue
                    if(curX-1 >= 0 && !visited.contains(new Pair<Integer, Integer>(curX-1, curY)) 
                        && canMove(heightMap.get(curX).get(curY), heightMap.get(curX-1).get(curY))) {
                        visited.add(new Pair<Integer, Integer>(curX-1, curY));
                        nextMoves.add(new Pair<Integer, Integer>(curX-1, curY));
                    }
                    if(curX+1 < heightMap.size() && !visited.contains(new Pair<Integer, Integer>(curX+1, curY)) 
                        && canMove(heightMap.get(curX).get(curY), heightMap.get(curX+1).get(curY))) {
                        visited.add(new Pair<Integer, Integer>(curX+1, curY));
                        nextMoves.add(new Pair<Integer, Integer>(curX+1, curY));
                    }
                    if(curY-1 >= 0 && !visited.contains(new Pair<Integer, Integer>(curX, curY-1)) 
                        && canMove(heightMap.get(curX).get(curY), heightMap.get(curX).get(curY-1))) {
                        visited.add(new Pair<Integer, Integer>(curX, curY-1));
                        nextMoves.add(new Pair<Integer, Integer>(curX, curY-1));
                    }
                    if(curY+1 < heightMap.get(curX).size() && !visited.contains(new Pair<Integer, Integer>(curX, curY+1)) 
                        && canMove(heightMap.get(curX).get(curY), heightMap.get(curX).get(curY+1))) {
                        visited.add(new Pair<Integer, Integer>(curX, curY+1));
                        nextMoves.add(new Pair<Integer, Integer>(curX, curY+1));
                    }
                }
            }

            // System.out.format("The shortest path is %d\n", numberOfSteps);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean canMove(char current, char next) {
        if(current >= 'a' && current <= 'z' && next != 'E')
            return current+1 >= next;
        else if(current == 'S')
            return next == 'a';
        else // next == 'E'
            return current == 'z';
    }

}