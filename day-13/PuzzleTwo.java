import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 13");

            ListOrInt dividerOne = new ListOrInt(null, "[[2]]");
            ListOrInt dividerTwo = new ListOrInt(null, "[[6]]");
            PriorityQueue<ListOrInt> orderedPackets = new PriorityQueue<ListOrInt>();
            orderedPackets.add(dividerOne);
            orderedPackets.add(dividerTwo);
            String line1, line2;
            while((line1 = br.readLine()) != null) {
                // Process two lines at a time?
                line2 = br.readLine();

                ListOrInt packet1 = new ListOrInt(null, line1);
                ListOrInt packet2 = new ListOrInt(null, line2);

                orderedPackets.add(packet1);
                orderedPackets.add(packet2);
                // When in the right order
                    // sumCorrectIndex += index + (index + 1)

                // When in the wrong order
                    // continue;

                // if(packet1.checkOrder(packet2) < 0)

                br.readLine(); // skip empty line between packets
            }

            int decoderKey = 1;
            int i = 1;
            while(orderedPackets.size() > 0) {
                ListOrInt packet = orderedPackets.remove();
                if(packet.checkOrder(dividerOne) == 0) {
                    System.out.println("KSDJF:LKSDJF:LK");
                    decoderKey *= i;
                }
                else if(packet.checkOrder(dividerTwo) == 0)
                    decoderKey *= i;

                System.out.println(packet);
                i++;
            }

            System.out.format("Decoder key %d", decoderKey);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}