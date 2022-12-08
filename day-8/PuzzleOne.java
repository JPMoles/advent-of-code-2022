import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-8/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 8");
            
            // 99x99 grid
            // 99 rows x 99 columns
            Node[][] forest = new Node[99][99];

            String line;
            int row = 0;
            while((line = br.readLine()) != null) {
                for(int column = 0; column < line.length(); column++)
                    forest[row][column] = new Node(Integer.parseInt(line.substring(column, column+1)));
                row++;
            }

            // for(int i = 0; i < forest.length; i++) {
            //     for(int j = 0; j < forest[i].length; j++) {
            //         System.out.print(forest[i][j].val);
            //     }
            //     System.out.println();
            // }

            // row by row, left to right
            int highestFound = -1;
            for(int i = 0; i < forest.length; i++) { // column length
                for(int j = 0; j < forest[i].length; j++) { // row length
                    if(forest[i][j].val > highestFound) { // strictly greater
                        // visible
                        forest[i][j].isVisible = true;
                        // Set highest left
                        forest[i][j].highestLeft = highestFound;
                        // change highest to this nodes val
                        highestFound = forest[i][j].val;
                    } else {
                        forest[i][j].highestLeft = highestFound;
                    }
                }
                highestFound = -1;
            }

            // row by row, right to left
            highestFound = -1;
            for(int i = 0; i < forest.length; i++) {
                for(int j = forest[i].length - 1; j >= 0; j--) {
                    if(forest[i][j].val > highestFound) { // strictly greater
                        // visible
                        forest[i][j].isVisible = true;
                        // Set highest right
                        forest[i][j].highestRight = highestFound;
                        // change highest to this nodes val
                        highestFound = forest[i][j].val;
                    } else {
                        forest[i][j].highestRight = highestFound;
                    }
                }
                highestFound = -1;
            }

            // column by column, top to bottom
            highestFound = -1;
            for(int i = 0; i < forest[0].length; i++) {
                for(int j = 0; j < forest.length; j++) {
                    if(forest[j][i].val > highestFound) { // strictly greater
                        // visible
                        forest[j][i].isVisible = true;
                        // Set highest top
                        forest[j][i].highestTop = highestFound;
                        // change highest to this nodes val
                        highestFound = forest[j][i].val;
                    } else {
                        forest[j][i].highestTop = highestFound;
                    }
                }
                highestFound = -1;
            }

            // column by column, bottom to top
            highestFound = -1;
            for(int i = 0; i < forest[0].length; i++) {
                for(int j = forest.length - 1; j >= 0; j--) {
                    if(forest[j][i].val > highestFound) { // strictly greater
                        // visible
                        forest[j][i].isVisible = true;
                        // Set highest top
                        forest[j][i].highestBottom = highestFound;
                        // change highest to this nodes val
                        highestFound = forest[j][i].val;
                    } else {
                        forest[j][i].highestBottom = highestFound;
                    }
                }
                highestFound = -1;
            }

            // row by row, left to right
            int amountVisible = 0;
            for(int i = 0; i < forest.length; i++) {
                for(int j = 0; j < forest[i].length; j++) {
                    if(forest[i][j].isVisible)
                        amountVisible++;
                }
            }

            System.out.format("Amount visible: %d", amountVisible);

            // Need to store each tree and height as a part of a graph or [][]
            // 2d array, with each square a node with left, right, top, bottom
            //
            // left, right, top, bottom - represent the highest height tree found in that direction
            // and the node should identify if it's visible from that direction as well

            // Could loop over the entire array
            // row by row from left to right
            // row by row from right to left
            // column by column from top to bottom
            // column by column from bottom to top

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}