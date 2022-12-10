import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 10");
            
            int registerX = 1;
            int cycle = 1;
            int signalStrength = 0;
            
            // row x col (really 40 arrays of length 6, but I like this for printing)
            // left most pixel is 0, right most is 39
            // sprite is 3 pixels wide, x is middle of sprite
            // char[][] screen = new char[40][6];
            // maybe one long array of length 40 * 6 would be better?
            char[] screen = new char[40 * 6];


            String line;
            String[] tokens;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                tokens = line.split(" ");
                if(tokens[0].equals("noop")) { // handle noop operation
                    // beginning of cycle

                    // current x
                    signalStrength = cycle * registerX;
                    System.out.format("In cycle #%d x is %d with signal of %d\n", cycle, registerX, signalStrength);
                    

                    // Filler character
                    screen[cycle - 1] = '.';

                    // at cycle it prints to position in row (cycle-1)%40 and position in col (cycle-1)/40
                    if((cycle-1)%40 == registerX) {
                        screen[cycle - 1] = '#';
                    } else if((cycle-1)%40 == registerX-1) {
                        screen[cycle - 1] = '#';
                    } else if((cycle-1)%40 == registerX+1) {
                        screen[cycle - 1] = '#';
                    }
                    
                    // end of cycle
                    cycle++;
                } else { // handle addx
                    int addVal = Integer.parseInt(tokens[1]);
                    for(int i = 0; i < 2; i++) {
                        // beginning of cycle

                        // current x
                        signalStrength = cycle * registerX;
                        System.out.format("In cycle #%d x is %d with signal of %d\n", cycle, registerX, signalStrength);
                    
                        // Filler character
                        // screen[(cycle-1) % 40][(cycle-1) / 40] = '.';
                        screen[cycle - 1] = '.';

                        // at cycle it prints to position in row (cycle-1)%40 and position in col (cycle-1)/40
                        if((cycle-1)%40 == registerX) {
                            screen[cycle - 1] = '#';
                        } else if((cycle-1)%40 == registerX-1) {
                            screen[cycle - 1] = '#';
                        } else if((cycle-1)%40 == registerX+1) {
                            screen[cycle - 1] = '#';
                        }
                        
                        // end of cycle
                        if(i == 1)
                            registerX += addVal;

                        cycle++;
                    }
                }
            }

            for(int i = 0; i < screen.length; i++) {
                if(i % 40 == 0)
                    System.out.println();
                
                System.out.print("" + screen[i]);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}