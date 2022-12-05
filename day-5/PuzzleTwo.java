import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PuzzleTwo {

    public static void main(String[] args) {

        List<Stack<Character>> crates = readInCrates();

        System.out.println(crates.size());
        
        // Read in moves as tokens and perform actions
        crates = performMoves(crates);

        // Read off crates from top
        System.out.println("Top of all stacks: ");
        for(int i = 0; i < crates.size(); i++) {
            System.out.print(crates.get(i).pop());
        }

    }

    private static List<Stack<Character>> performMoves(List<Stack<Character>> crates) {
        
        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);


            int counter = 0;
            while((br.readLine()) != null && counter < 9)
                counter++;

            String line;
            while((line = br.readLine()) != null) {
                if(line == "")
                    break;
                
                String[] tokens = line.split(" ");

                // tokens[0] "moves"
                // tokens[1] amount to move
                // tokens[2] "from"
                // tokens[3] stack to move from
                // tokens[4] "to"
                // tokens[5] stack to move to
                int amountToMove = Integer.parseInt(tokens[1]);
                int moveFrom = Integer.parseInt(tokens[3]) - 1;
                int moveTo = Integer.parseInt(tokens[5]) - 1;
                Stack<Character> temp = new Stack<>();
                for(int i = 0; i < amountToMove; i++) {
                    temp.push(crates.get(moveFrom).pop());
                }
                while(temp.size() > 0) {
                    crates.get(moveTo).push(temp.pop());
                }
            }
            

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return crates;
    }

    private static List<Stack<Character>> readInCrates() {

        List<Stack<Character>> crates = new ArrayList<Stack<Character>>();
        Stack<String> lines = new Stack<>();
        
        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);


            int counter = 0;
            while((lines.push(br.readLine())) != null && counter < 8)
                counter++;

            for(int i = 0; i < 9; i++) {
                crates.add(new Stack<Character>());
            }

            String line;
            while(lines.size() > 0) {
                // 3 characters in a row
                line = lines.pop(); // get next line up
                counter = 0; // reset counter for each line
                
                while(counter < (9 * 4)) {
                    char crateLeft = line.charAt(counter);
                    char letter = line.charAt(counter + 1);
                    char crateRight = line.charAt(counter + 2);

                    // Check letter exists
                    if(letter >= 65 && letter <= 90) {
                        crates.get(counter / 4).push(letter);
                    }

                    counter += 4; // one for each letter plus space
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return crates;
    }

}