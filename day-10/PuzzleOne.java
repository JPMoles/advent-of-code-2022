import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOne {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 10");
            
            int registerX = 1;
            int cycle = 1;
            int signalStrength = 0;

            String line;
            String[] tokens;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                tokens = line.split(" ");
                if(tokens[0].equals("noop")) { // handle noop operation
                    // beginning of cycle

                    // current x
                    System.out.format("In cycle #%d x is: %d\n", cycle, registerX);
                    if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
                        signalStrength += cycle * registerX;
                    }
                    
                    // end of cycle
                    cycle++;
                } else { // handle addx
                    int addVal = Integer.parseInt(tokens[1]);
                    for(int i = 0; i < 2; i++) {
                        // beginning of cycle

                        // current x
                        System.out.format("In cycle #%d x is: %d\n", cycle, registerX);
                        if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
                            signalStrength += cycle * registerX;
                        }
                        
                        // end of cycle
                        if(i == 1)
                            registerX += addVal;

                        cycle++;
                    }
                }
            }

            System.out.format("Total Signal Strength: %d\n", signalStrength);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}