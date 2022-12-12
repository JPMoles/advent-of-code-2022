import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PuzzleTwo {

    public static void main(String[] args) {

        try {
            FileReader fs = new FileReader("./day-99/input.txt");
            BufferedReader br = new BufferedReader(fs);

            System.out.println("Day 11");

            ArrayList<MonkeyTwo> monkeys = new ArrayList<MonkeyTwo>();
            
            String line;
            while((line = br.readLine()) != null) {
                // read in monkeys and starting state
                String[] tokens = line.split(" ");
                int id = Integer.parseInt(tokens[1].substring(0, 1));

                // Next line is starting items
                line = br.readLine();
                tokens = line.split(" ");
                Queue<BigInteger> items = new LinkedList<BigInteger>();
                for(int i = 4; i < tokens.length; i++) // all numbers in starting items
                    items.add(new BigInteger(tokens[i].replace(",", "")));

                line = br.readLine();
                tokens = line.split(" ");
                char operation = tokens[6].charAt(0);
                String operationAmount = tokens[7];

                line = br.readLine();
                tokens = line.split(" ");
                String divisibleBy = tokens[5];

                line = br.readLine();
                tokens = line.split(" ");
                int positiveTestMonkey = Integer.parseInt(tokens[9]);

                line = br.readLine();
                tokens = line.split(" ");
                int negativeTestMonkey = Integer.parseInt(tokens[9]);

                line = br.readLine(); // go past empty line

                monkeys.add(new MonkeyTwo(id, items, operation, operationAmount, divisibleBy, positiveTestMonkey, negativeTestMonkey));
            }

            // Perform 20 rounds
            for(int j = 0; j < 10_000; j++) {
                // 1 Round
                for(int i = 0; i < monkeys.size(); i++) {
                    MonkeyTwo m = monkeys.get(i);
    
                    // Monkey throw all items
                    while(m.items.size() > 0) {
                        BigInteger item = m.items.remove();
                        item = m.performOperation(item); // operation
                        if(item.signum() < 0)
                            System.out.println("Got negative number");
                        // item /= 3;// divide by 3 from monkey being bored with item
                        int monkeyToThrowTo = m.performTest(item); // perform test
                        item = item.mod(new BigInteger("9699690")); // new mod to remove numbers greater than multiple of all primes
                        monkeys.get(monkeyToThrowTo).items.add(item); // throw to monkey
                        m.itemsInspected++;
                    }
                }
                
                // if(j == 0 || j == 19 || (j+1) % 1000 == 0)
                // printMonkeys(monkeys, j+1); // monkeys, round
            }
            
            Collections.sort(monkeys);
            for(MonkeyTwo m : monkeys) {
                System.out.format("Monkey %d has %d items inspected\n", m.id, m.itemsInspected);
            }

            String highest = String.valueOf(monkeys.get(monkeys.size() - 1).itemsInspected);
            String secondHighest = String.valueOf(monkeys.get(monkeys.size() - 2).itemsInspected);

            // System.out.format("Monkey business: %d", highest * secondHighest);
            System.out.println(new BigInteger(highest).multiply(new BigInteger(secondHighest)));

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printMonkeys(ArrayList<MonkeyTwo> monkeys, int round) {
        System.out.format("After round %d\n", round);
        for(MonkeyTwo m : monkeys) {
            System.out.format("Monkey %d inspected items %d times.\n", m.id, m.itemsInspected);
        }
    }

}