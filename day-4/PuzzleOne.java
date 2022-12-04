import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 4");

            // Id number ranges for cleaning, each elf has a pair
            
            // Identify number ranges where one range completely
            // contains the other

            int completeOverlap = 0;
            int firstRangeStart, firstRangeEnd;
            int secondRangeStart, secondRangeEnd;
            String line;
            while((line = br.readLine()) != null) {
                // Parse the first range start and end
                firstRangeStart = Integer.parseInt((String)line.subSequence(0, line.indexOf("-")));
                firstRangeEnd = Integer.parseInt((String)line.subSequence(line.indexOf("-") + 1, line.indexOf(",")));
                secondRangeStart = Integer.parseInt((String)line.subSequence(line.indexOf(",") + 1, line.lastIndexOf("-")));
                secondRangeEnd = Integer.parseInt((String)line.subSequence(line.lastIndexOf("-") + 1, line.length()));
                // Parse the second range start and end
                
                // If the first range contains the second range
                if(firstRangeStart <= secondRangeStart && firstRangeEnd >= secondRangeEnd) {
                    // Found one, break
                    completeOverlap++;
                } else if(secondRangeStart <= firstRangeStart && secondRangeEnd >= firstRangeEnd) { // If the second range contains the first range
                    // Found one, break
                    completeOverlap++;
                }
            }

            System.out.println(completeOverlap);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}