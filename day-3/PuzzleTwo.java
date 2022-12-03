import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 3");

            // To help prioritize item rearrangement items are given priorities
            // a-z (1-26), A-Z (27-52)

            // Elves divided into groups of three, badge to show group
            // Badge is the only item type carried by all three elves
            // Priorities still apply to each item type

            // Find the sum of the priorities of the badges for each group

            Set<Character> commonItem1 = new HashSet<Character>();
            Set<Character> commonItem2 = new HashSet<Character>();
            int priorityBadgeSum = 0;

            String line1, line2, line3;
            while((line1 = br.readLine()) != null && (line2 = br.readLine()) != null && (line3 = br.readLine()) != null) {
                for(int i = 0; i < line1.length(); i++)
                    commonItem1.add(line1.charAt(i));
                for(int i = 0; i < line2.length(); i++) {
                    if(commonItem1.contains(line2.charAt(i)))
                        commonItem2.add(line2.charAt(i));
                }
                for(int i = 0; i < line3.length(); i++) {
                    if(commonItem2.contains(line3.charAt(i))) {
                        priorityBadgeSum += letterPriority(line3.charAt(i));
                        break;
                    }
                }
                commonItem1.clear();
                commonItem2.clear();
            }

            System.out.println(priorityBadgeSum);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int letterPriority(char c) {
        return c < 91 ? c - 38 : c - 96;
    }

}
