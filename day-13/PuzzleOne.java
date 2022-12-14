import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 13");

            int index = 1;
            int sumCorrectIndex = 0;
            String line1, line2;
            while((line1 = br.readLine()) != null) {
                // Process two lines at a time?
                line2 = br.readLine();

                ListOrInt packet1 = new ListOrInt(null, line1);
                ListOrInt packet2 = new ListOrInt(null, line2);

                // When in the right order
                    // sumCorrectIndex += index + (index + 1)

                // When in the wrong order
                    // continue;

                if(packet1.checkOrder(packet2) < 0)
                    sumCorrectIndex += index; // both indicies are in the right order

                index++; // move forward 2 packets each time
                br.readLine(); // skip empty line between packets
            }

            System.out.format("Total of correct indicies is %d", sumCorrectIndex);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}