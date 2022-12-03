import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleTwo {
    
    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
            System.out.println("Day 2");

            // Opponent:
            // A - Rock, B - Paper, C - Scissors
            // 1 point   2 points   3 points

            // You:
            // X - Lose, Y - Draw, Z - Win

            String line;
            int scoreSum = 0;

            while((line = br.readLine()) != null) {
                char foe = line.charAt(0); // Read the first letter
                char you = line.charAt(2); // Read the second letter

                // Add to score based on result of match
                scoreSum += you == 'X' ? 0 : (you == 'Y' ? 3 : 6);
                
                // Add to score based on option picked
                if(you == 'Y') {
                    // If draw -> pick same letter
                    scoreSum += choiceToPoints(foe);
                } else if(you == 'Z') {
                    // If win -> pick next letter, 1 2 3 + 0 0 1
                    foe += 1; // next letter, 65 -> 66, 66 -> 67, 67 -> 68
                    foe = (char) ((foe % 68) + ((foe / 68) * 65)); // 66 -> 66, 67 -> 67, 68 -> 65
                    scoreSum += choiceToPoints(foe);
                } else {
                    // If lose -> pick previous letter
                    scoreSum += choiceToPoints(previousLetter(foe));
                }
            }

            System.out.println("Total score: " + scoreSum);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int choiceToPoints(char c) {
        return c == 'A' ? 1 : (c == 'B' ? 2 : 3);
    }

    private static char previousLetter(char c) {
        return c == 'A' ? 'C' : (c == 'B' ? 'A' : 'B');
    }
}
