import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOne {
    
    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

            System.out.println("Day 2");

            // Opponent:
            // A - Rock, B - Paper, C - Scissors

            // You:
            // X - Rock, Y - Paper, Z - Scissors

            String line;
            int scoreSum = 0;

            while((line = br.readLine()) != null) {
                char foe = line.charAt(0); // Read the first letter
                char you = line.charAt(2); // Read the second letter

                // Add to score based on your choice
                scoreSum += you == 'X' ? 1 : (you == 'Y' ? 2 : 3);
                
                // Add to score based on lose, tie, win
                // Possible outcomes:
                // You tie: A - X, B - Y, C - Z (all 23)
                // You win: A - Y (24), B - Z (24), C - X (21)
                // You lose: A - Z (25), B - X (22), C - Y (22)

                if(you - foe == 23) {
                    scoreSum += 3; // Tie
                } else if(you - foe == 24 || you - foe == 21) {
                    scoreSum += 6; // Win
                } else {
                    scoreSum += 0; // Lose
                }
            }

            System.out.println("Total score: " + scoreSum);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
}
