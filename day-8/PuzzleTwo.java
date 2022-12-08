import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleTwo {

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

            // Need to find the highest scenic score of each tree
            // 
            // Scenic score is found by multiplying the number of trees
            // that can be seen from the tree in each of the four directions.
            //
            // Edge trees see 0 distance to the edge (so they won't be highest)

            int highestScenicScore = 0;
            // only loop over inside trees
            for(int i = 1; i < forest.length - 1; i++) { 
                for(int j = 1; j < forest[i].length - 1; j++) {
                    int treeScore = 1; // Calculate scenic score

                    // count trees going left
                    int treesFound = 0;
                    for(int left = 1; j-left >= 0; left++) {
                        if(forest[i][j-left].val >= forest[i][j].val) {
                            treesFound++;
                            break;
                        } else {
                            treesFound++;
                        }
                    }
                    treeScore *= treesFound;
                    // count trees going right
                    treesFound = 0;
                    for(int right = 1; j+right < forest[i].length; right++) {
                        if(forest[i][j+right].val >= forest[i][j].val) {
                            treesFound++;
                            break;
                        } else {
                            treesFound++;
                        }
                    }
                    treeScore *= treesFound;
                    // count trees going up
                    treesFound = 0;
                    for(int up = 1; i-up >= 0; up++) {
                        if(forest[i-up][j].val >= forest[i][j].val) {
                            treesFound++;
                            break;
                        } else {
                            treesFound++;
                        }
                    }
                    treeScore *= treesFound;
                    // count trees going down
                    treesFound = 0;
                    for(int down = 1; i+down < forest.length; down++) {
                        if(forest[i+down][j].val >= forest[i][j].val) {
                            treesFound++;
                            break;
                        } else {
                            treesFound++;
                        }
                    }
                    treeScore *= treesFound;

                    if(treeScore > highestScenicScore)
                        highestScenicScore = treeScore;
                }
            }

            System.out.format("\nHighest scenic score is %d\n", highestScenicScore);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
