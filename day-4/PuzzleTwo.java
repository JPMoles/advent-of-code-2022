import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 4");

            // Id number ranges for cleaning, each elf has a pair
            
            // Identify number ranges where one range partially
            // contains the other

            int completeOverlap = 0;
            int firstRangeStart, firstRangeEnd;
            int secondRangeStart, secondRangeEnd;
            String line;
            while((line = br.readLine()) != null) {
                // Parse the first range start and end
                firstRangeStart = Integer.parseInt((String)line.subSequence(0, line.indexOf("-")));
                firstRangeEnd = Integer.parseInt((String)line.subSequence(line.indexOf("-") + 1, line.indexOf(",")));
                // Parse the second range start and end
                secondRangeStart = Integer.parseInt((String)line.subSequence(line.indexOf(",") + 1, line.lastIndexOf("-")));
                secondRangeEnd = Integer.parseInt((String)line.subSequence(line.lastIndexOf("-") + 1, line.length()));
                
                // Easier condition:
                // If first range start and end are below second range start
                // or first range start and end are greater than second range end
                // Then negate for all other conditions
                if(!(firstRangeEnd < secondRangeStart || firstRangeStart > secondRangeEnd)) {
                    completeOverlap++;
                }

                
                // // If the first range contains the second range
                // if(firstRangeStart <= secondRangeStart && firstRangeEnd >= secondRangeEnd) {
                //     // Found one, break
                //     completeOverlap++;
                // } else if(secondRangeStart <= firstRangeStart && secondRangeEnd >= firstRangeEnd) { // If the second range contains the first range
                //     // Found one, break
                //     completeOverlap++;
                // } else if(firstRangeStart <= secondRangeStart && firstRangeEnd >= secondRangeStart) {
                //     completeOverlap++;
                // } else if(firstRangeStart <= secondRangeEnd && firstRangeEnd >= secondRangeEnd) {
                //     completeOverlap++;
                // }
                // // Partial overlap with range one to two


            }

            System.out.println(completeOverlap);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}