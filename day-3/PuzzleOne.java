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

            System.out.println("Day 3");

            // Each rucksack has two large compartments
            // Items of a given type go into one of the two compartments
            // Elf failed to do this for exactly one item per rucksack

            // letter refers to item, case sensitive, each rucksack on a new line
            // Always even number of items, First half of items in first compartment, second in second

            // can have duplicates of common item in both compartments

            // To help prioritize item rearrangement items are given priorities
            // a-z (1-26), A-Z (27-52)

            // Find the sum of the prioritizes of common items

            Set<Character> commonItem = new HashSet<Character>();
            int prioritySum = 0;

            String line;
            while((line = br.readLine()) != null) {
                for(int i = 0; i < line.length() / 2; i++)
                    commonItem.add(line.charAt(i));
                for(int i = line.length() / 2; i < line.length(); i++) {
                    if(commonItem.contains(line.charAt(i))) {
                        prioritySum += letterPriority(line.charAt(i));
                        break;
                    }
                }
                commonItem.clear();
            }

            System.out.println(prioritySum);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int letterPriority(char c) {
        return c < 91 ? c - 38 : c - 96;
    }

}