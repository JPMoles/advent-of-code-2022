import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);
            
            int charactersTraversed = 0;
            Set<Character> foundFour = new HashSet<Character>();

            String line = br.readLine();
            for(int i = 3; i < line.length(); i++) {
                for(int j = i; j >= i-3; j--) {
                    if(!foundFour.add(line.charAt(j))) {
                        foundFour.clear();
                        break;
                    }
                }
                if(foundFour.size() == 4) {
                    charactersTraversed = i + 1; // add 1 for 1 based index
                    break;
                }
            }

            System.out.println("Found marker at: " + charactersTraversed);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}